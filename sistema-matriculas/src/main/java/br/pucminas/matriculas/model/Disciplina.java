package br.pucminas.matriculas.model;

import br.pucminas.matriculas.model.enums.StatusDisciplina;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Disciplina ofertada por um curso (US02, US10).
 *
 * Regras que esta entidade carrega:
 * - no maximo 60 alunos inscritos; ao atingir o limite as inscricoes sao encerradas;
 * - no minimo 3 alunos ao final do periodo, senao a disciplina e cancelada.
 */
@Entity
@Table(name = "disciplina")
public class Disciplina {

    public static final int CAPACIDADE_MAXIMA = 60;
    public static final int MINIMO_ALUNOS = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int creditos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDisciplina tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDisciplina status = StatusDisciplina.PLANEJADA;

    @Column(nullable = false)
    private int capacidadeMaxima = CAPACIDADE_MAXIMA;

    @Column(nullable = false)
    private int minimoAlunos = MINIMO_ALUNOS;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Matricula> matriculas = new ArrayList<>();

    protected Disciplina() {
    }

    public Disciplina(String codigo, String nome, int creditos, TipoDisciplina tipo, Curso curso) {
        this.codigo = codigo;
        this.nome = nome;
        this.creditos = creditos;
        this.tipo = tipo;
        this.curso = curso;
    }

    /**
     * Numero de alunos com matricula ativa nesta disciplina.
     */
    public int getTotalMatriculados() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Numero de vagas ainda disponiveis (capacidade maxima menos matriculados).
     */
    public int getVagasDisponiveis() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Indica se a disciplina ainda aceita novas matriculas.
     */
    public boolean aceitaNovaMatricula() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Indica se a disciplina atingiu o minimo de alunos para ocorrer no semestre.
     */
    public boolean atingiuMinimoAlunos() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public TipoDisciplina getTipo() {
        return tipo;
    }

    public void setTipo(TipoDisciplina tipo) {
        this.tipo = tipo;
    }

    public StatusDisciplina getStatus() {
        return status;
    }

    public void setStatus(StatusDisciplina status) {
        this.status = status;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getMinimoAlunos() {
        return minimoAlunos;
    }

    public void setMinimoAlunos(int minimoAlunos) {
        this.minimoAlunos = minimoAlunos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
