package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Matricula;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.repository.MatriculaRepository;
import br.pucminas.matriculas.integration.SistemaCobrancaClient;
import java.util.List;
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
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Cancela uma matricula do aluno, liberando a vaga.
     * So e permitido dentro do periodo de matriculas.
     */
    public void cancelar(Aluno aluno, Disciplina disciplina, Semestre semestre) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Matriculas ativas do aluno no semestre.
     */
    public List<Matricula> listarPorAluno(Aluno aluno, Semestre semestre) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Matriculas ativas de uma disciplina, base para a consulta do professor (US07).
     */
    public List<Matricula> listarPorDisciplina(Disciplina disciplina) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
