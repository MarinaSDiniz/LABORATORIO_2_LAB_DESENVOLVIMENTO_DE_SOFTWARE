package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Curriculo;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Semestre;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso aos curriculos gerados pela secretaria (US08).
 */
@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {

    Optional<Curriculo> findByCursoAndSemestre(Curso curso, Semestre semestre);

    List<Curriculo> findBySemestre(Semestre semestre);
}
