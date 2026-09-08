package br.pucminas.matriculas.integration;

import org.springframework.stereotype.Component;

/**
 * Implementacao de mentira do Sistema de Cobrancas usada no prototipo (US12).
 * Registra a notificacao no console no lugar de chamar um sistema externo real.
 */
@Component
public class SistemaCobrancaMock implements SistemaCobrancaClient {

    @Override
    public void notificarMatricula(NotificacaoCobranca notificacao) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
