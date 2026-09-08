package br.pucminas.matriculas.model;

import br.pucminas.matriculas.model.enums.Perfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Aluno da universidade (US03, US04, US05).
 * Pode cursar ate 4 disciplinas obrigatorias e ate 2 optativas por semestre.
 */
@Entity
@Table(name = "aluno")
public class Aluno extends Usuario {

    public static final int MAX_DISCIPLINAS_OBRIGATORIAS = 4;
    public static final int MAX_DISCIPLINAS_OPTATIVAS = 2;

    @Column(nullable = false, unique = true)
    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Matricula> matriculas = new ArrayList<>();

    protected Aluno() {
        super();
    }

    public Aluno(String nome, String login, String senha, String matricula, Curso curso) {
        super(nome, login, senha, Perfil.ALUNO);
        this.matricula = matricula;
        this.curso = curso;
    }

    /**
     * Conta as matriculas ativas do aluno no semestre por tipo de disciplina,
     * usado para validar os limites de 4 obrigatorias e 2 optativas.
     */
    public int contarMatriculasAtivas(Semestre semestre, br.pucminas.matriculas.model.enums.TipoDisciplina tipo) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
