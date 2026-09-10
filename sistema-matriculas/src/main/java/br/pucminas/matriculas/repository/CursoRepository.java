package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Curso;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso aos cursos da universidade (US09).
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNome(String nome);

    boolean existsByNome(String nome);
}
