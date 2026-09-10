package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Usuario;
import br.pucminas.matriculas.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * US01 - Login no sistema.
 * Valida as credenciais e mantem o usuario autenticado durante a sessao do prototipo.
 */
@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;

    private Usuario usuarioAutenticado;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Valida login e senha. Lanca CredenciaisInvalidasException se nao conferirem.
     */
    public Usuario autenticar(String login, String senha) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Usuario atualmente autenticado, se houver.
     */
    public Optional<Usuario> getUsuarioAutenticado() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Encerra a sessao do usuario autenticado.
     */
    public void encerrarSessao() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
