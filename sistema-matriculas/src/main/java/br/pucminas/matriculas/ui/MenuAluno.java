package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.AlunoController;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;

/**
 * Menu do aluno (US02, US03, US04, US05, US08).
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
            console.exibir("1 - Disciplinas  2 - Matricular  3 - Cancelar  4 - Minhas matriculas  5 - Curriculo  0 - Sair");
            try {
                switch (console.lerInteiro("Opcao")) {
                    case 1 -> listarDisciplinas();
                    case 2 -> matricular(aluno);
                    case 3 -> cancelarMatricula(aluno);
                    case 4 -> listarMinhasMatriculas(aluno);
                    case 5 -> consultarCurriculo(aluno);
                    case 0 -> sair = true;
                    default -> console.exibirErro("Opcao invalida");
                }
            } catch (NoSuchElementException ex) {
                throw ex;
            } catch (RuntimeException ex) {
                console.exibirErro(ex.getMessage());
            }
        }
    }

    /** US02 */
    public void listarDisciplinas() {
        for (Disciplina disciplina : alunoController.consultarDisciplinasDisponiveis()) {
            String vagas = disciplina.getVagasDisponiveis() == 0
                    ? "inscricoes encerradas"
                    : "vagas: " + disciplina.getTotalMatriculados() + " ocupadas / "
                        + disciplina.getVagasDisponiveis() + " disponiveis";
            console.exibir(disciplina.getId() + " - " + disciplina.getNome() + " (curso "
                    + disciplina.getCurso().getNome() + ", " + disciplina.getTipo() + ", " + vagas + ")");
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
        var matriculas = alunoController.consultarMinhasMatriculas(aluno);
        if (matriculas.isEmpty()) {
            console.exibir("Nenhuma matricula ativa.");
        }
        matriculas.forEach(matricula ->
            console.exibir(matricula.getDisciplina().getId() + " - " + matricula.getDisciplina().getNome()
                + " (" + matricula.getTipo() + ")"));
    }

    /** US08 */
    public void consultarCurriculo(Aluno aluno) {
        alunoController.consultarCurriculo(aluno).ifPresentOrElse(
            curriculo -> curriculo.getDisciplinas().forEach(disciplina ->
                console.exibir(disciplina.getId() + " - " + disciplina.getNome() + " (" + disciplina.getTipo() + ")")),
            () -> console.exibir("Curriculo do seu curso ainda nao foi gerado."));
    }
}
