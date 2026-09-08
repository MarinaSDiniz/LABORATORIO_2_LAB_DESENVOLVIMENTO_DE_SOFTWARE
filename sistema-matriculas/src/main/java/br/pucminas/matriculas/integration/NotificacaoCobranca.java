package br.pucminas.matriculas.integration;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Semestre;
import java.util.List;

/**
 * Dados enviados ao Sistema de Cobrancas a cada matricula concluida (US12):
 * aluno, semestre e disciplinas matriculadas.
 *
 * Nao e uma entidade: e apenas o conteudo da mensagem trocada com o sistema externo.
 */
public record NotificacaoCobranca(Aluno aluno, Semestre semestre, List<Disciplina> disciplinas) {
}
