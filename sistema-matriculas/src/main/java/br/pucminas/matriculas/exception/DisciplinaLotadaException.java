package br.pucminas.matriculas.exception;

/**
 * Lancada quando a disciplina ja atingiu 60 alunos inscritos (US03).
 */
public class DisciplinaLotadaException extends MatriculaException {

    public DisciplinaLotadaException(String mensagem) {
        super(mensagem);
    }
}
