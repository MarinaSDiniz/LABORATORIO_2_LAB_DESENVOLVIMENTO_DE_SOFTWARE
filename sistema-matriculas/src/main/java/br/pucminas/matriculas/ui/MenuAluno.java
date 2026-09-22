package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.AlunoController;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
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
        boolean sair = false;
        while (!sair) {
            console.exibirTitulo("Menu do aluno");
            console.exibir("1 - Disciplinas  2 - Matricular  3 - Cancelar  4 - Minhas matriculas  0 - Sair");
            switch (console.lerInteiro("Opcao")) {
                case 1 -> listarDisciplinas();
                case 2 -> matricular(aluno);
                case 3 -> cancelarMatricula(aluno);
                case 4 -> listarMinhasMatriculas(aluno);
                case 0 -> sair = true;
                default -> console.exibirErro("Opcao invalida");
            }
        }
    }

    /** US02 */
    public void listarDisciplinas() {
        for (Disciplina disciplina : alunoController.consultarDisciplinasDisponiveis()) {
            console.exibir(disciplina.getId() + " - " + disciplina.getNome() + " ("
                    + disciplina.getTipo() + ", vagas: " + disciplina.getVagasDisponiveis() + ")");
        }
    }

    /** US03 e US04 */
    public void matricular(Aluno aluno) {
        try {
            alunoController.matricular(aluno, console.lerId("Id da disciplina"));
            console.exibir("Matricula realizada.");
        } catch (RuntimeException ex) {
            console.exibirErro(ex.getMessage());
        }
    }

    /** US05 */
    public void cancelarMatricula(Aluno aluno) {
        try {
            alunoController.cancelarMatricula(aluno, console.lerId("Id da disciplina"));
            console.exibir("Matricula cancelada.");
        } catch (RuntimeException ex) {
            console.exibirErro(ex.getMessage());
        }
    }

    public void listarMinhasMatriculas(Aluno aluno) {
        alunoController.consultarMinhasMatriculas(aluno).forEach(matricula ->
            console.exibir(matricula.getDisciplina().getId() + " - " + matricula.getDisciplina().getNome()));
    }
}
