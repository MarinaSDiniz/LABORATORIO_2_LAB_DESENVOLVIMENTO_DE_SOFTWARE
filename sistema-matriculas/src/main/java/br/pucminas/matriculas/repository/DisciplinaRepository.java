package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.enums.StatusDisciplina;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso as disciplinas ofertadas (US02, US07, US10).
 */
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Optional<Disciplina> findByCodigo(String codigo);

    List<Disciplina> findByCurso(Curso curso);

    List<Disciplina> findByProfessor(Professor professor);

    List<Disciplina> findByTipo(TipoDisciplina tipo);

    List<Disciplina> findByStatus(StatusDisciplina status);
}
