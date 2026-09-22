package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Usuario;
import br.pucminas.matriculas.exception.CredenciaisInvalidasException;
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
        Usuario usuario = usuarioRepository.findByLogin(login)
            .filter(candidato -> candidato.autenticar(senha))
            .orElseThrow(() -> new CredenciaisInvalidasException("Login ou senha invalidos"));
        usuarioAutenticado = usuario;
        return usuario;
    }

    /**
     * Usuario atualmente autenticado, se houver.
     */
    public Optional<Usuario> getUsuarioAutenticado() {
        return Optional.ofNullable(usuarioAutenticado);
    }

    /**
     * Encerra a sessao do usuario autenticado.
     */
    public void encerrarSessao() {
        usuarioAutenticado = null;
    }
}
