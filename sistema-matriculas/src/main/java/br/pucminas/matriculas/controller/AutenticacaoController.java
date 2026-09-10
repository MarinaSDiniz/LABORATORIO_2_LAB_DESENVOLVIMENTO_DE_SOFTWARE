package br.pucminas.matriculas.controller;

import br.pucminas.matriculas.model.Usuario;
import br.pucminas.matriculas.service.AutenticacaoService;
import org.springframework.stereotype.Controller;

/**
 * US01 - Login no sistema.
 * Traduz a entrada do menu em chamadas ao servico de autenticacao.
 * Nao valida credenciais: quem valida e o AutenticacaoService.
 */
@Controller
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    public Usuario login(String login, String senha) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public void logout() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
