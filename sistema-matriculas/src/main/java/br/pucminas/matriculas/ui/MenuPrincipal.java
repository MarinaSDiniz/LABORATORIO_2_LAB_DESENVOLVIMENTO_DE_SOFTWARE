package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.AutenticacaoController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Porta de entrada da interface de linha de comando.
 * Faz o login (US01) e encaminha o usuario ao menu do seu perfil.
 *
 * No Lab01S02 apenas confirma que o contexto e o banco subiram; o fluxo de menus
 * e implementado no Lab01S03.
 */
@Component
public class MenuPrincipal implements CommandLineRunner {

    private final ConsoleIO console;
    private final AutenticacaoController autenticacaoController;
    private final MenuAluno menuAluno;
    private final MenuProfessor menuProfessor;
    private final MenuSecretaria menuSecretaria;

    public MenuPrincipal(ConsoleIO console,
                         AutenticacaoController autenticacaoController,
                         MenuAluno menuAluno,
                         MenuProfessor menuProfessor,
                         MenuSecretaria menuSecretaria) {
        this.console = console;
        this.autenticacaoController = autenticacaoController;
        this.menuAluno = menuAluno;
        this.menuProfessor = menuProfessor;
        this.menuSecretaria = menuSecretaria;
    }

    @Override
    public void run(String... args) {
        System.out.println("=========================================");
        System.out.println(" Sistema de Matriculas - PUC Minas");
        System.out.println(" Lab01S02: estrutura de classes e stubs");
        System.out.println(" Interface e regras: Lab01S03");
        System.out.println("=========================================");
    }

    /**
     * Le login e senha e autentica o usuario.
     */
    public void exibirLogin() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Direciona para o menu correspondente ao perfil do usuario autenticado.
     */
    public void direcionarPorPerfil() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
