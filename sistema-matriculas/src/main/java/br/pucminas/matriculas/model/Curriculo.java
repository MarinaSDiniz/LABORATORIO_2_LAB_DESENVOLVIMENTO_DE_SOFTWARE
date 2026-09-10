package br.pucminas.matriculas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

/**
 * Curriculo gerado pela secretaria (US08): as disciplinas que um curso oferece
 * em um determinado semestre. E o que o aluno consulta para se matricular.
 */
@Entity
@Table(name = "curriculo", uniqueConstraints = @UniqueConstraint(columnNames = {"curso_id", "semestre_id"}))
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

    @ManyToMany
    @JoinTable(
            name = "curriculo_disciplina",
            joinColumns = @JoinColumn(name = "curriculo_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    private List<Disciplina> disciplinas = new ArrayList<>();

    protected Curriculo() {
    }

    public Curriculo(Curso curso, Semestre semestre) {
        this.curso = curso;
        this.semestre = semestre;
    }

    /**
     * Inclui uma disciplina na oferta do semestre.
     */
    public void adicionarDisciplina(Disciplina disciplina) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
