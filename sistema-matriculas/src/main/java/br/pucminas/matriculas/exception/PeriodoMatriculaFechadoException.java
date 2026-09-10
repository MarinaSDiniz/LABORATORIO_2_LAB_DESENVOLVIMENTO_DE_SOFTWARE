package br.pucminas.matriculas.exception;

/**
 * Lancada quando ha tentativa de matricula ou cancelamento fora do periodo de matriculas (US05).
 */
public class PeriodoMatriculaFechadoException extends MatriculaException {

    public PeriodoMatriculaFechadoException(String mensagem) {
        super(mensagem);
    }
}
