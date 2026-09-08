package br.pucminas.matriculas.model;

import br.pucminas.matriculas.model.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Professor da universidade (US07).
 * Consulta os alunos matriculados nas disciplinas que leciona.
 */
@Entity
@Table(name = "professor")
public class Professor extends Usuario {

    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplinas = new ArrayList<>();

    protected Professor() {
        super();
    }

    public Professor(String nome, String login, String senha) {
        super(nome, login, senha, Perfil.PROFESSOR);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
