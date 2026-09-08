package br.pucminas.matriculas.integration;

/**
 * Porta de saida para o Sistema de Cobrancas (US12).
 *
 * O Sistema de Cobrancas e um ator externo no diagrama de caso de uso: o sistema
 * de matriculas apenas o notifica, nao conhece como a cobranca e gerada. Por isso
 * a dependencia e declarada como interface.
 */
public interface SistemaCobrancaClient {

    /**
     * Notifica o sistema externo de que o aluno se inscreveu para o semestre.
     */
    void notificarMatricula(NotificacaoCobranca notificacao);
}
