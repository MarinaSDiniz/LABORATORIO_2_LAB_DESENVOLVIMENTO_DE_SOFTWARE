package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Curso;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso aos alunos cadastrados (US11).
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByMatricula(String matricula);

    Optional<Aluno> findByLogin(String login);

    List<Aluno> findByCurso(Curso curso);
}
