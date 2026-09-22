package br.pucminas.matriculas.ui;

import br.pucminas.matriculas.controller.AutenticacaoController;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Professor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import br.pucminas.matriculas.config.CargaInicial;


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
    private final CargaInicial cargaInicial;


    public MenuPrincipal(ConsoleIO console,
                         AutenticacaoController autenticacaoController,
                         MenuAluno menuAluno,
                         MenuProfessor menuProfessor,
                         MenuSecretaria menuSecretaria, CargaInicial cargaInicial) {
        this.console = console;
        this.autenticacaoController = autenticacaoController;
        this.menuAluno = menuAluno;
        this.menuProfessor = menuProfessor;
        this.menuSecretaria = menuSecretaria;
        this.cargaInicial = cargaInicial;
    }

    @Override
    public void run(String... args) {
        System.out.println("=========================================");
        System.out.println(" Sistema de Matriculas - PUC Minas");
        System.out.println(" Lab01S03: regras e interface");
        System.out.println("=========================================");
        
        cargaInicial.carregar();
        exibirLogin();
    }

    /**
     * Le login e senha e autentica o usuario.
     */
    public void exibirLogin() {
        try {
            autenticacaoController.login(console.lerTexto("Login"), console.lerSenha("Senha"));
            direcionarPorPerfil();
        } catch (RuntimeException ex) {
            console.exibirErro(ex.getMessage());
        }
    }

    /**
     * Direciona para o menu correspondente ao perfil do usuario autenticado.
     */
    public void direcionarPorPerfil() {
        var usuario = autenticacaoController.getUsuarioAutenticado().orElse(null);
        if (usuario == null) {
            return;
        }
        switch (usuario.getPerfil()) {
            case ALUNO -> menuAluno.exibir((Aluno) usuario);
            case PROFESSOR -> menuProfessor.exibir((Professor) usuario);
            case SECRETARIA -> menuSecretaria.exibir();
        }
    }
}
