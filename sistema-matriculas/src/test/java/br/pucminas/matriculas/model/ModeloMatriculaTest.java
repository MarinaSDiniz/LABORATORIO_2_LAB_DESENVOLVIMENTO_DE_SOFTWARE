package br.pucminas.matriculas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.pucminas.matriculas.model.enums.StatusSemestre;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ModeloMatriculaTest {

    @Test
    void contaVagasEStatusDaDisciplina() {
        Curso curso = new Curso("Computacao", 40);
        Disciplina disciplina = new Disciplina("CMP001", "Algoritmos", 4,
                TipoDisciplina.OBRIGATORIA, curso);
        Semestre semestre = new Semestre(2026, 1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        semestre.setStatus(StatusSemestre.MATRICULAS_ABERTAS);
        Aluno aluno = new Aluno("Ana", "ana", "senha", "1", curso);

        assertEquals(60, disciplina.getVagasDisponiveis());
        Matricula matricula = new Matricula(aluno, disciplina, semestre, disciplina.getTipo());
        disciplina.getMatriculas().add(matricula);

        assertEquals(1, disciplina.getTotalMatriculados());
        assertEquals(59, disciplina.getVagasDisponiveis());
        assertTrue(disciplina.aceitaNovaMatricula());

        matricula.cancelar();
        assertFalse(matricula.isAtiva());
        assertEquals(0, disciplina.getTotalMatriculados());
    }

    @Test
    void periodoIncluiAsDatasDeInicioEFim() {
        LocalDate inicio = LocalDate.of(2026, 9, 1);
        LocalDate fim = LocalDate.of(2026, 9, 10);
        Semestre semestre = new Semestre(2026, 2, inicio, fim);
        semestre.setStatus(StatusSemestre.MATRICULAS_ABERTAS);

        assertTrue(semestre.periodoMatriculaAberto(inicio));
        assertTrue(semestre.periodoMatriculaAberto(fim));
        assertFalse(semestre.periodoMatriculaAberto(fim.plusDays(1)));
    }
}
