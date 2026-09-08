package br.pucminas.matriculas.exception;

/**
 * Superclasse das excecoes de regra de negocio do sistema de matriculas.
 * Permite que a camada de interface trate qualquer violacao de regra de forma uniforme.
 */
public class MatriculaException extends RuntimeException {

    public MatriculaException(String mensagem) {
        super(mensagem);
    }
}
