package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.SecretariaController;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import java.util.Arrays;
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
        boolean sair = false;
        while (!sair) {
            console.exibirTitulo("Menu da secretaria");
            console.exibir("1 - Curso 2 - Disciplina 3 - Aluno 4 - Professor 5 - Curriculo 6 - Encerrar matriculas 0 - Sair");
            switch (console.lerInteiro("Opcao")) {
                case 1 -> cadastrarCurso();
                case 2 -> cadastrarDisciplina();
                case 3 -> cadastrarAluno();
                case 4 -> cadastrarProfessor();
                case 5 -> gerarCurriculo();
                case 6 -> encerrarPeriodoMatriculas();
                case 0 -> sair = true;
                default -> console.exibirErro("Opcao invalida");
            }
        }
    }

    /** US09 */
    public void cadastrarCurso() {
        secretariaController.cadastrarCurso(console.lerTexto("Nome"), console.lerInteiro("Creditos"));
    }

    /** US10 */
    public void cadastrarDisciplina() {
        secretariaController.cadastrarDisciplina(console.lerTexto("Codigo"), console.lerTexto("Nome"),
            console.lerInteiro("Creditos"), TipoDisciplina.valueOf(console.lerTexto("Tipo (OBRIGATORIA/OPTATIVA").toUpperCase()),
            console.lerId("Id do curso"));
    }

    /** US11 */
    public void cadastrarAluno() {
        secretariaController.cadastrarAluno(console.lerTexto("Nome"), console.lerTexto("Login"),
            console.lerSenha("Senha"), console.lerTexto("Matricula"), console.lerId("Id do curso"));
    }

    /** US11 */
    public void cadastrarProfessor() {
        secretariaController.cadastrarProfessor(console.lerTexto("Nome"), console.lerTexto("Login"), console.lerSenha("Senha"));
    }

    /** US08 */
    public void gerarCurriculo() {
        Long cursoId = console.lerId("Id do curso");
        Long semestreId = console.lerId("Id do semestre");
        String ids = console.lerTexto("Ids das disciplinas separados por virgula");
        secretariaController.gerarCurriculo(cursoId, semestreId,
            Arrays.stream(ids.split(",")).map(String::trim).map(Long::valueOf).toList());
    }

    /** US06 */
    public void encerrarPeriodoMatriculas() {
        secretariaController.encerrarPeriodoMatriculas(console.lerId("Id do semestre"));
    }
}
