package br.pucminas.matriculas.exception;

/**
 * Lancada quando o aluno tenta uma 5a disciplina obrigatoria ou uma 3a optativa (US03, US04).
 */
public class LimiteDisciplinasExcedidoException extends MatriculaException {

    public LimiteDisciplinasExcedidoException(String mensagem) {
        super(mensagem);
    }
}
