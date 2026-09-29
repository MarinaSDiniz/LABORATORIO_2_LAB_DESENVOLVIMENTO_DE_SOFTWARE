package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.repository.DisciplinaRepository;
import br.pucminas.matriculas.repository.MatriculaRepository;
import br.pucminas.matriculas.repository.SemestreRepository;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import br.pucminas.matriculas.exception.PeriodoMatriculaFechadoException;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Matricula;
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
     * e devolve cada disciplina cancelada com os alunos que precisam ser notificados.
     */
    public Map<Disciplina, List<Aluno>> encerrarMatriculas(Long semestreId) {
        Semestre semestre = buscar(semestreId);
        if (semestre.getStatus() != StatusSemestre.MATRICULAS_ABERTAS) {
            throw new PeriodoMatriculaFechadoException("Periodo de matriculas ja encerrado");
        }
        List<Disciplina> emOferta = disciplinaRepository.findAll().stream()
            .filter(disciplina -> disciplina.getStatus() != StatusDisciplina.CANCELADA
                && disciplina.getStatus() != StatusDisciplina.ATIVA)
            .toList();
        Map<Disciplina, List<Aluno>> canceladas = new LinkedHashMap<>();
        for (Disciplina disciplina : emOferta) {
            List<Matricula> ativas = matriculaRepository.findByDisciplinaAndStatusComAluno(
                disciplina, StatusMatricula.ATIVA);
            if (ativas.size() >= disciplina.getMinimoAlunos()) {
                disciplina.setStatus(StatusDisciplina.ATIVA);
                continue;
            }
            disciplina.setStatus(StatusDisciplina.CANCELADA);
            ativas.forEach(Matricula::cancelar);
            matriculaRepository.saveAll(ativas);
            canceladas.put(disciplina, ativas.stream().map(Matricula::getAluno).toList());
        }
        disciplinaRepository.saveAll(emOferta);
        semestre.setStatus(StatusSemestre.ENCERRADO);
        semestreRepository.save(semestre);
        return canceladas;
    }

    public List<Semestre> listar() {
        return semestreRepository.findAll();
    }

    /**
     * Semestre com periodo de matriculas aberto, se houver.
     */
    public Semestre buscarSemestreAtivo() {
        return semestreRepository.findByStatus(StatusSemestre.MATRICULAS_ABERTAS).stream()
                .findFirst().orElseThrow(() -> new PeriodoMatriculaFechadoException("Periodo de matriculas fechado"));
    }

    public Semestre buscarPorId(Long id) {
        return buscar(id);
    }

    private Semestre buscar(Long id) {
        return semestreRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Semestre nao encontrado: " + id));
    }
}
