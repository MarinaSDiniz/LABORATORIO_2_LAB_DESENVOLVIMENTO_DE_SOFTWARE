package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Professor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso aos professores cadastrados (US07, US11).
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByLogin(String login);
}
