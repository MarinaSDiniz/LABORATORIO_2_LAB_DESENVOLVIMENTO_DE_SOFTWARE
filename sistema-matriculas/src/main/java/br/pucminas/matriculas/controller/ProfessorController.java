package br.pucminas.matriculas.controller;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.service.ProfessorService;
import java.util.List;
import org.springframework.stereotype.Controller;

/**
 * US07 - Consultar alunos matriculados nas disciplinas do professor.
 */
@Controller
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    public List<Disciplina> consultarMinhasDisciplinas(Professor professor) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public List<Aluno> consultarAlunosMatriculados(Professor professor, Long disciplinaId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
