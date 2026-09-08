package br.pucminas.matriculas.model.enums;

/**
 * Situacao do semestre letivo em relacao ao periodo de matriculas.
 *
 * PLANEJADO           - curriculo em montagem, matriculas ainda fechadas.
 * MATRICULAS_ABERTAS  - alunos podem se matricular e cancelar matriculas.
 * ENCERRADO           - periodo fechado; disciplinas ja foram ativadas ou canceladas.
 */
public enum StatusSemestre {
    PLANEJADO,
    MATRICULAS_ABERTAS,
    ENCERRADO
}
