package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.ProfessorController;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.Disciplina;
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
            switch (console.lerInteiro("Opcao")) {
                case 1 -> listarAlunosPorDisciplina(professor);
                case 0 -> sair = true;
                default -> console.exibirErro("Opcao invalida");
            }
        }
    }

    /** US07 */
    public void listarAlunosPorDisciplina(Professor professor) {
        for (Disciplina disciplina : professorController.consultarMinhasDisciplinas(professor)) {
            console.exibir(disciplina.getId() + " - " + disciplina.getNome());
        }
        Long disciplinaId = console.lerId("Id da disciplina");
        professorController.consultarAlunosMatriculados(professor, disciplinaId)
                .forEach(aluno -> console.exibir(aluno.getNome()));
    }
}
