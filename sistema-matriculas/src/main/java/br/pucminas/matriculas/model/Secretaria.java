package br.pucminas.matriculas.model;

import br.pucminas.matriculas.model.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Secretaria da universidade (US06, US08, US09, US10, US11).
 * Mantem cursos, disciplinas, professores e alunos, e gera o curriculo do semestre.
 */
@Entity
@Table(name = "secretaria")
public class Secretaria extends Usuario {

    protected Secretaria() {
        super();
    }

    public Secretaria(String nome, String login, String senha) {
        super(nome, login, senha, Perfil.SECRETARIA);
    }
}
