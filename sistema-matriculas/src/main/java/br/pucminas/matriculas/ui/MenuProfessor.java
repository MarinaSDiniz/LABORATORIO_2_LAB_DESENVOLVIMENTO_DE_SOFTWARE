package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.ProfessorController;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Aluno;
import java.util.List;
import java.util.NoSuchElementException;
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
        boolean sair = false;
        while (!sair) {
            console.exibirTitulo("Menu do professor");
            console.exibir("1 - Alunos por disciplina  0 - Sair");
            try {
                switch (console.lerInteiro("Opcao")) {
                    case 1 -> listarAlunosPorDisciplina(professor);
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

    /** US07 */
    public void listarAlunosPorDisciplina(Professor professor) {
        List<Disciplina> disciplinas = professorController.consultarMinhasDisciplinas(professor);
        if (disciplinas.isEmpty()) {
            console.exibir("Nenhuma disciplina vinculada a voce.");
            return;
        }
        for (Disciplina disciplina : disciplinas) {
            console.exibir(disciplina.getId() + " - " + disciplina.getNome());
        }
        Long disciplinaId = console.lerId("Id da disciplina");
        List<Aluno> alunos = professorController.consultarAlunosMatriculados(professor, disciplinaId);
        if (alunos.isEmpty()) {
            console.exibir("Nenhum aluno matriculado.");
        }
        alunos.forEach(aluno -> console.exibir(aluno.getNome()));
    }
}
