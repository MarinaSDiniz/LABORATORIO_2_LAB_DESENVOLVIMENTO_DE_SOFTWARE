package br.pucminas.matriculas.repository;

import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.StatusSemestre;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso aos semestres letivos e seus periodos de matricula (US05, US06).
 */
@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {

    Optional<Semestre> findByAnoAndPeriodo(int ano, int periodo);

    List<Semestre> findByStatus(StatusSemestre status);
}
