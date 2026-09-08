package br.pucminas.matriculas.exception;

/**
 * Lancada quando o login ou a senha informados nao conferem (US01).
 */
public class CredenciaisInvalidasException extends MatriculaException {

    public CredenciaisInvalidasException(String mensagem) {
        super(mensagem);
    }
}
