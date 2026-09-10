package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso a usuarios de qualquer perfil. Usado na validacao de login (US01)
 * e na regra de login unico (US11).
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

    boolean existsByLogin(String login);
}
