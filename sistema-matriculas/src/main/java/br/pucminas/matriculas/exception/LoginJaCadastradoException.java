package br.pucminas.matriculas.exception;

/**
 * Lancada ao tentar cadastrar dois usuarios com o mesmo login (US11).
 */
public class LoginJaCadastradoException extends MatriculaException {

    public LoginJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
