package br.pucminas.matriculas.controller;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Matricula;
import br.pucminas.matriculas.service.DisciplinaService;
import br.pucminas.matriculas.service.MatriculaService;
import br.pucminas.matriculas.service.SemestreService;
import java.util.List;
import org.springframework.stereotype.Controller;

/**
 * Operacoes disponiveis ao aluno (US02, US03, US04, US05).
 */
@Controller
public class AlunoController {

    private final DisciplinaService disciplinaService;
    private final MatriculaService matriculaService;
    private final SemestreService semestreService;

    public AlunoController(DisciplinaService disciplinaService,
                           MatriculaService matriculaService,
                           SemestreService semestreService) {
        this.disciplinaService = disciplinaService;
        this.matriculaService = matriculaService;
        this.semestreService = semestreService;
    }

    /**
     * US02 - disciplinas ofertadas no periodo, com vagas e tipo.
     */
    public List<Disciplina> consultarDisciplinasDisponiveis() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * US03 e US04 - matricula em disciplina obrigatoria ou optativa.
     */
    public Matricula matricular(Aluno aluno, Long disciplinaId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * US05 - cancelamento de matricula dentro do periodo.
     */
    public void cancelarMatricula(Aluno aluno, Long disciplinaId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Matriculas ativas do aluno no semestre corrente.
     */
    public List<Matricula> consultarMinhasMatriculas(Aluno aluno) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
