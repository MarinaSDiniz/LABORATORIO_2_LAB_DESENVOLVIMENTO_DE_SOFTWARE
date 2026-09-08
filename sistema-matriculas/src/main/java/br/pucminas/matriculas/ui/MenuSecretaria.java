package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.SecretariaController;
import org.springframework.stereotype.Component;

/**
 * Menu da secretaria (US06, US08, US09, US10, US11).
 */
@Component
public class MenuSecretaria {

    private final ConsoleIO console;
    private final SecretariaController secretariaController;

    public MenuSecretaria(ConsoleIO console, SecretariaController secretariaController) {
        this.console = console;
        this.secretariaController = secretariaController;
    }

    public void exibir() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US09 */
    public void cadastrarCurso() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US10 */
    public void cadastrarDisciplina() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US11 */
    public void cadastrarAluno() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US11 */
    public void cadastrarProfessor() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US08 */
    public void gerarCurriculo() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US06 */
    public void encerrarPeriodoMatriculas() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
