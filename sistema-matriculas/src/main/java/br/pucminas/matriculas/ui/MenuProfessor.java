package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.ProfessorController;
import br.pucminas.matriculas.model.Professor;
import org.springframework.stereotype.Component;

/**
 * Menu do professor (US07).
 */
@Component
public class MenuProfessor {

    private final ConsoleIO console;
    private final ProfessorController professorController;

    public MenuProfessor(ConsoleIO console, ProfessorController professorController) {
        this.console = console;
        this.professorController = professorController;
    }

    public void exibir(Professor professor) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US07 */
    public void listarAlunosPorDisciplina(Professor professor) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
