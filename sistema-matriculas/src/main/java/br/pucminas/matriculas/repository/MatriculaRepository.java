package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Matricula;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.StatusMatricula;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso as matriculas (US03, US04, US05, US06, US07).
 * As contagens aqui sustentam os limites de 4 obrigatorias, 2 optativas,
 * minimo de 3 e maximo de 60 alunos por disciplina.
 */
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByAlunoAndSemestreAndStatus(Aluno aluno, Semestre semestre, StatusMatricula status);

    List<Matricula> findByDisciplinaAndStatus(Disciplina disciplina, StatusMatricula status);

    Optional<Matricula> findByAlunoAndDisciplinaAndSemestre(Aluno aluno, Disciplina disciplina, Semestre semestre);

    long countByAlunoAndSemestreAndTipoAndStatus(
            Aluno aluno, Semestre semestre, TipoDisciplina tipo, StatusMatricula status);

    long countByDisciplinaAndStatus(Disciplina disciplina, StatusMatricula status);
}
