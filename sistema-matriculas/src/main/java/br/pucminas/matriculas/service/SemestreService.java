package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.repository.DisciplinaRepository;
import br.pucminas.matriculas.repository.MatriculaRepository;
import br.pucminas.matriculas.repository.SemestreRepository;
import java.time.LocalDate;
import java.util.List;
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
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Abre o periodo de matriculas do semestre.
     */
    public Semestre abrirMatriculas(Long semestreId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Encerra o periodo: ativa as disciplinas com no minimo 3 alunos, cancela as demais
     * e devolve a lista de disciplinas canceladas para que os alunos sejam notificados.
     */
    public List<Disciplina> encerrarMatriculas(Long semestreId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Semestre com periodo de matriculas aberto, se houver.
     */
    public Semestre buscarSemestreAtivo() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
