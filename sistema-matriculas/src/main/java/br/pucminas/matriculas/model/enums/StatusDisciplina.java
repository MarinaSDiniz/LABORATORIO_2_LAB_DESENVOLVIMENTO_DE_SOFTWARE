package br.pucminas.matriculas.model.enums;

/**
 * Ciclo de vida de uma disciplina dentro de um semestre.
 *
 * PLANEJADA - ofertada no curriculo, aceitando matriculas.
 * LOTADA    - atingiu 60 alunos; inscricoes encerradas automaticamente.
 * ATIVA     - fechou o periodo com no minimo 3 alunos; vai ocorrer.
 * CANCELADA - fechou o periodo com menos de 3 alunos; nao vai ocorrer.
 */
public enum StatusDisciplina {
    PLANEJADA,
    LOTADA,
    ATIVA,
    CANCELADA
}
