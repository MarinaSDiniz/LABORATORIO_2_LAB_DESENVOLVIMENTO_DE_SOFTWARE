package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.repository.DisciplinaRepository;
import br.pucminas.matriculas.repository.MatriculaRepository;
import br.pucminas.matriculas.repository.SemestreRepository;
import java.time.LocalDate;
import java.util.List;
import br.pucminas.matriculas.exception.EntidadeNaoEncontradaException;
import br.pucminas.matriculas.model.enums.StatusDisciplina;
import br.pucminas.matriculas.model.enums.StatusMatricula;
import br.pucminas.matriculas.model.enums.StatusSemestre;
import org.springframework.stereotype.Service;

/**
 * US06 - Encerramento automatico do periodo de matriculas.
 *
 * Ao encerrar o periodo, cada disciplina com 3 ou mais alunos inscritos vira ATIVA
 * e as demais viram CANCELADA; os alunos das canceladas precisam ser notificados.
 */
@Service
public class SemestreService {

    private final SemestreRepository semestreRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final MatriculaRepository matriculaRepository;

    public SemestreService(SemestreRepository semestreRepository,
                           DisciplinaRepository disciplinaRepository,
                           MatriculaRepository matriculaRepository) {
        this.semestreRepository = semestreRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.matriculaRepository = matriculaRepository;
    }

    /**
     * Cria um semestre letivo com seu periodo de matriculas.
     */
    public Semestre criar(int ano, int periodo, LocalDate inicioMatriculas, LocalDate fimMatriculas) {
        if (ano <= 0 || (periodo != 1 && periodo != 2)
                || inicioMatriculas == null || fimMatriculas == null
                || fimMatriculas.isBefore(inicioMatriculas)) {
            throw new IllegalArgumentException("Dados do semestre invalidos");
        }
        if (semestreRepository.findByAnoAndPeriodo(ano, periodo).isPresent()) {
            throw new IllegalArgumentException("Semestre ja cadastrado");
        }
        return semestreRepository.save(new Semestre(ano, periodo, inicioMatriculas, fimMatriculas));
    }

    /**
     * Abre o periodo de matriculas do semestre.
     */
    public Semestre abrirMatriculas(Long semestreId) {
        Semestre semestre = buscar(semestreId);
        semestre.setStatus(StatusSemestre.MATRICULAS_ABERTAS);
        return semestreRepository.save(semestre);
    }

    /**
     * Encerra o periodo: ativa as disciplinas com no minimo 3 alunos, cancela as demais
     * e devolve a lista de disciplinas canceladas para que os alunos sejam notificados.
     */
    public List<Disciplina> encerrarMatriculas(Long semestreId) {
        Semestre semestre = buscar(semestreId);
        List<Disciplina> canceladas = disciplinaRepository.findAll().stream()
            .filter(disciplina -> disciplina.getStatus() != StatusDisciplina.CANCELADA
                && disciplina.getStatus() != StatusDisciplina.ATIVA)
            .filter(disciplina -> {
                boolean ocorre = matriculaRepository.countByDisciplinaAndStatus(
                    disciplina, StatusMatricula.ATIVA) >= disciplina.getMinimoAlunos();
                disciplina.setStatus(ocorre ? StatusDisciplina.ATIVA : StatusDisciplina.CANCELADA);
                return !ocorre;
            }).toList();
        canceladas.forEach(disciplina -> matriculaRepository.findByDisciplinaAndStatus(disciplina, StatusMatricula.ATIVA)
            .forEach(matricula -> {
                matricula.cancelar();
                matriculaRepository.save(matricula);
            }));
        disciplinaRepository.saveAll(disciplinaRepository.findAll());
        semestre.setStatus(StatusSemestre.ENCERRADO);
        semestreRepository.save(semestre);
        return canceladas;
    }

    /**
     * Semestre com periodo de matriculas aberto, se houver.
     */
    public Semestre buscarSemestreAtivo() {
        return semestreRepository.findByStatus(StatusSemestre.MATRICULAS_ABERTAS).stream()
                .findFirst().orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum semestre com matriculas abertas"));
    }

    public Semestre buscarPorId(Long id) {
        return buscar(id);
    }

    private Semestre buscar(Long id) {
        return semestreRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Semestre nao encontrado: " + id));
    }
}
