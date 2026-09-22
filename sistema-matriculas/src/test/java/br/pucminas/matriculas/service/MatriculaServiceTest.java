package br.pucminas.matriculas.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.pucminas.matriculas.exception.LimiteDisciplinasExcedidoException;
import br.pucminas.matriculas.integration.NotificacaoCobranca;
import br.pucminas.matriculas.integration.SistemaCobrancaClient;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Matricula;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.StatusSemestre;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import br.pucminas.matriculas.repository.MatriculaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @Mock
    private SistemaCobrancaClient sistemaCobrancaClient;

    private MatriculaService service;
    private Aluno aluno;
    private Disciplina disciplina;
    private Semestre semestre;

    @BeforeEach
    void setUp() {
        service = new MatriculaService(matriculaRepository, sistemaCobrancaClient);
        Curso curso = new Curso("Computacao", 40);
        aluno = new Aluno("Ana", "ana", "senha", "1", curso);
        disciplina = new Disciplina("CMP001", "Algoritmos", 4, TipoDisciplina.OBRIGATORIA, curso);
        semestre = new Semestre(2026, 2, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        semestre.setStatus(StatusSemestre.MATRICULAS_ABERTAS);
        when(matriculaRepository.findByAlunoAndDisciplinaAndSemestre(aluno, disciplina, semestre))
                .thenReturn(Optional.empty());
    }

    @Test
    void matriculaNotificaCobranca() {
        when(matriculaRepository.save(any(Matricula.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(matriculaRepository.findByAlunoAndSemestreAndStatus(any(), any(), any()))
            .thenAnswer(invocation -> new ArrayList<>(aluno.getMatriculas()));
        Matricula matricula = service.matricular(aluno, disciplina, semestre);

        assertTrue(matricula.isAtiva());
        verify(sistemaCobrancaClient).notificarMatricula(any(NotificacaoCobranca.class));
    }

    @Test
    void impedeQuintaObrigatoria() {
        for (int i = 0; i < Aluno.MAX_DISCIPLINAS_OBRIGATORIAS; i++) {
            Disciplina outra = new Disciplina("CMP00" + i, "Disciplina " + i, 4,
                    TipoDisciplina.OBRIGATORIA, aluno.getCurso());
            Matricula existente = new Matricula(aluno, outra, semestre, TipoDisciplina.OBRIGATORIA);
            aluno.getMatriculas().add(existente);
        }

        assertThrows(LimiteDisciplinasExcedidoException.class,
                () -> service.matricular(aluno, disciplina, semestre));
    }
}
