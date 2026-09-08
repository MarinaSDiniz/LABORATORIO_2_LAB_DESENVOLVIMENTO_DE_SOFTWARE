package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.AlunoController;
import br.pucminas.matriculas.model.Aluno;
import org.springframework.stereotype.Component;

/**
 * Menu do aluno (US02, US03, US04, US05).
 */
@Component
public class MenuAluno {

    private final ConsoleIO console;
    private final AlunoController alunoController;

    public MenuAluno(ConsoleIO console, AlunoController alunoController) {
        this.console = console;
        this.alunoController = alunoController;
    }

    public void exibir(Aluno aluno) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US02 */
    public void listarDisciplinas() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US03 e US04 */
    public void matricular(Aluno aluno) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US05 */
    public void cancelarMatricula(Aluno aluno) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public void listarMinhasMatriculas(Aluno aluno) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
