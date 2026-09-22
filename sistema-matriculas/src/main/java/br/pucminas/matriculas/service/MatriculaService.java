package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Matricula;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.StatusMatricula;
import br.pucminas.matriculas.exception.DisciplinaLotadaException;
import br.pucminas.matriculas.exception.EntidadeNaoEncontradaException;
import br.pucminas.matriculas.exception.LimiteDisciplinasExcedidoException;
import br.pucminas.matriculas.exception.PeriodoMatriculaFechadoException;
import br.pucminas.matriculas.integration.NotificacaoCobranca;
import java.time.LocalDate;
import java.util.List;
import br.pucminas.matriculas.repository.MatriculaRepository;
import br.pucminas.matriculas.integration.SistemaCobrancaClient;
import org.springframework.stereotype.Service;

/**
 * US03 - Matricular-se em disciplina obrigatoria (limite de 4).
 * US04 - Matricular-se em disciplina optativa (limite de 2).
 * US05 - Cancelar matricula dentro do periodo.
 * US12 - Notificar o Sistema de Cobrancas a cada matricula concluida.
 *
 * Concentra as regras de negocio de matricula. Nenhuma validacao de limite,
 * vaga ou periodo deve ficar no controller ou na interface.
 */
@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final SistemaCobrancaClient sistemaCobrancaClient;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            SistemaCobrancaClient sistemaCobrancaClient) {
        this.matriculaRepository = matriculaRepository;
        this.sistemaCobrancaClient = sistemaCobrancaClient;
    }

    /**
     * Matricula o aluno na disciplina, validando nesta ordem:
     * periodo de matriculas aberto, limite do tipo (4 obrigatorias / 2 optativas),
     * matricula duplicada e vagas disponiveis (maximo 60).
     * Ao concluir, notifica o Sistema de Cobrancas.
     */
public Matricula matricular(Aluno aluno, Disciplina disciplina, Semestre semestre) {
    validarPeriodo(semestre);

    if (matriculaRepository
            .findByAlunoAndDisciplinaAndSemestre(aluno, disciplina, semestre)
            .isPresent()) {
        throw new IllegalArgumentException(
                "Aluno ja matriculado nesta disciplina");
    }

    int limite = disciplina.getTipo()
            == br.pucminas.matriculas.model.enums.TipoDisciplina.OBRIGATORIA
            ? Aluno.MAX_DISCIPLINAS_OBRIGATORIAS
            : Aluno.MAX_DISCIPLINAS_OPTATIVAS;

    long quantidadeAtual =
            matriculaRepository.countByAlunoAndSemestreAndTipoAndStatus(
                    aluno,
                    semestre,
                    disciplina.getTipo(),
                    StatusMatricula.ATIVA);

    if (quantidadeAtual >= limite) {
        throw new LimiteDisciplinasExcedidoException(
                "Limite de disciplinas "
                        + disciplina.getTipo()
                        + " excedido");
    }

    long totalMatriculados =
            matriculaRepository.countByDisciplinaAndStatus(
                    disciplina,
                    StatusMatricula.ATIVA);

    if (totalMatriculados >= disciplina.getCapacidadeMaxima()
            || disciplina.getStatus()
                == br.pucminas.matriculas.model.enums.StatusDisciplina.CANCELADA
            || disciplina.getStatus()
                == br.pucminas.matriculas.model.enums.StatusDisciplina.ATIVA) {
        throw new DisciplinaLotadaException(
                "Disciplina sem vagas: " + disciplina.getNome());
    }

    Matricula matricula = matriculaRepository.save(
            new Matricula(
                    aluno,
                    disciplina,
                    semestre,
                    disciplina.getTipo()));

    totalMatriculados++;

    if (totalMatriculados >= disciplina.getCapacidadeMaxima()) {
        disciplina.setStatus(
                br.pucminas.matriculas.model.enums.StatusDisciplina.LOTADA);
    }

    sistemaCobrancaClient.notificarMatricula(
            new NotificacaoCobranca(
                    aluno,
                    semestre,
                    listarPorAluno(aluno, semestre)
                            .stream()
                            .map(Matricula::getDisciplina)
                            .toList()));

    return matricula;
}


    /**
     * Cancela uma matricula do aluno, liberando a vaga.
     * So e permitido dentro do periodo de matriculas.
     */
    public void cancelar(Aluno aluno, Disciplina disciplina, Semestre semestre) {
        validarPeriodo(semestre);
        Matricula matricula = matriculaRepository.findByAlunoAndDisciplinaAndSemestre(aluno, disciplina, semestre)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Matricula nao encontrada"));
        matricula.cancelar();
        matriculaRepository.save(matricula);
        if (disciplina.getStatus() == br.pucminas.matriculas.model.enums.StatusDisciplina.LOTADA) {
            disciplina.setStatus(br.pucminas.matriculas.model.enums.StatusDisciplina.PLANEJADA);
        }
    }

    /**
     * Matriculas ativas do aluno no semestre.
     */
    public List<Matricula> listarPorAluno(Aluno aluno, Semestre semestre) {
    return matriculaRepository.findByAlunoAndSemestreAndStatusComDisciplina(
            aluno, semestre, StatusMatricula.ATIVA);
}

    /**
     * Matriculas ativas de uma disciplina, base para a consulta do professor (US07).
     */
    public List<Matricula> listarPorDisciplina(Disciplina disciplina) {
        return matriculaRepository.findByDisciplinaAndStatus(disciplina, StatusMatricula.ATIVA);
    }

    private void validarPeriodo(Semestre semestre) {
        if (semestre == null || !semestre.periodoMatriculaAberto(LocalDate.now())) {
            throw new PeriodoMatriculaFechadoException("Periodo de matriculas fechado");
        }
    }
}
