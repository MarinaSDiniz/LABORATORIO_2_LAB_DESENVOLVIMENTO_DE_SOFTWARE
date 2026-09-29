package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.SecretariaController;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;

/**
 * Menu da secretaria (US06, US07, US08, US09, US10, US11).
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
        boolean sair = false;
        while (!sair) {
            console.exibirTitulo("Menu da secretaria");
            console.exibir("1 - Curso  2 - Disciplina  3 - Aluno  4 - Professor  5 - Curriculo");
            console.exibir("6 - Encerrar matriculas  7 - Atribuir professor  8 - Listar cadastros  0 - Sair");
            try {
                switch (console.lerInteiro("Opcao")) {
                    case 1 -> cadastrarCurso();
                    case 2 -> cadastrarDisciplina();
                    case 3 -> cadastrarAluno();
                    case 4 -> cadastrarProfessor();
                    case 5 -> gerarCurriculo();
                    case 6 -> encerrarPeriodoMatriculas();
                    case 7 -> atribuirProfessor();
                    case 8 -> listarCadastros();
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

    /** US09 */
    public void cadastrarCurso() {
        Curso curso = secretariaController.cadastrarCurso(console.lerTexto("Nome"), console.lerInteiro("Creditos"));
        console.exibir("Curso cadastrado com id " + curso.getId());
    }

    /** US10 */
    public void cadastrarDisciplina() {
        Disciplina disciplina = secretariaController.cadastrarDisciplina(console.lerTexto("Codigo"),
            console.lerTexto("Nome"), console.lerInteiro("Creditos"), lerTipo(), console.lerId("Id do curso"));
        console.exibir("Disciplina cadastrada com id " + disciplina.getId() + " (60 vagas)");
    }

    /** US11 */
    public void cadastrarAluno() {
        Aluno aluno = secretariaController.cadastrarAluno(console.lerTexto("Nome"), console.lerTexto("Login"),
            console.lerSenha("Senha"), console.lerTexto("Matricula"), console.lerId("Id do curso"));
        console.exibir("Aluno cadastrado com id " + aluno.getId());
    }

    /** US11 */
    public void cadastrarProfessor() {
        Professor professor = secretariaController.cadastrarProfessor(console.lerTexto("Nome"),
            console.lerTexto("Login"), console.lerSenha("Senha"));
        console.exibir("Professor cadastrado com id " + professor.getId());
    }

    /** US08 */
    public void gerarCurriculo() {
        Long cursoId = console.lerId("Id do curso");
        Long semestreId = console.lerId("Id do semestre");
        String ids = console.lerTexto("Ids das disciplinas separados por virgula");
        List<Long> disciplinaIds;
        try {
            disciplinaIds = Arrays.stream(ids.split(",")).map(String::trim).map(Long::valueOf).toList();
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Informe os ids das disciplinas separados por virgula, ex.: 1,2,3");
        }
        secretariaController.gerarCurriculo(cursoId, semestreId, disciplinaIds);
        console.exibir("Curriculo gerado.");
    }

    /** US06 */
    public void encerrarPeriodoMatriculas() {
        Map<Disciplina, List<Aluno>> canceladas =
            secretariaController.encerrarPeriodoMatriculas(console.lerId("Id do semestre"));
        console.exibir("Periodo encerrado. Disciplinas com 3 ou mais alunos foram ativadas.");
        if (canceladas.isEmpty()) {
            console.exibir("Nenhuma disciplina cancelada.");
        }
        canceladas.forEach((disciplina, alunos) -> {
            console.exibir("Cancelada (menos de 3 alunos): " + disciplina.getNome());
            alunos.forEach(aluno -> console.exibir("  Notificado: " + aluno.getNome()));
        });
    }

    /** US07 */
    public void atribuirProfessor() {
        Disciplina disciplina = secretariaController.atribuirProfessor(
            console.lerId("Id da disciplina"), console.lerId("Id do professor"));
        console.exibir("Professor atribuido a " + disciplina.getNome());
    }

    public void listarCadastros() {
        console.exibir("Cursos:");
        for (Curso curso : secretariaController.listarCursos()) {
            console.exibir("  " + curso.getId() + " - " + curso.getNome() + " (" + curso.getCreditos() + " creditos)");
        }
        console.exibir("Disciplinas:");
        for (Disciplina disciplina : secretariaController.listarDisciplinas()) {
            console.exibir("  " + disciplina.getId() + " - " + disciplina.getNome() + " (" + disciplina.getTipo()
                + ", curso " + disciplina.getCurso().getNome() + ", " + disciplina.getStatus()
                + ", alunos: " + disciplina.getTotalMatriculados() + ")");
        }
        console.exibir("Professores:");
        for (Professor professor : secretariaController.listarProfessores()) {
            console.exibir("  " + professor.getId() + " - " + professor.getNome() + " (login " + professor.getLogin() + ")");
        }
        console.exibir("Semestres:");
        for (Semestre semestre : secretariaController.listarSemestres()) {
            console.exibir("  " + semestre.getId() + " - " + semestre.getAno() + "/" + semestre.getPeriodo()
                + " (" + semestre.getStatus() + ")");
        }
    }

    private TipoDisciplina lerTipo() {
        String tipo = console.lerTexto("Tipo (OBRIGATORIA/OPTATIVA)").toUpperCase();
        try {
            return TipoDisciplina.valueOf(tipo);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Tipo invalido: use OBRIGATORIA ou OPTATIVA");
        }
    }
}
