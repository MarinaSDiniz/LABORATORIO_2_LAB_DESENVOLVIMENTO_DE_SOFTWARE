package br.pucminas.matriculas.exception;

/**
 * Lancada quando um curso, disciplina, aluno ou semestre referenciado nao existe.
 */
public class EntidadeNaoEncontradaException extends MatriculaException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
