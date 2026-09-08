package br.pucminas.matriculas.model;

import br.pucminas.matriculas.model.enums.StatusMatricula;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
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
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Inscricao de um aluno em uma disciplina dentro de um semestre (US03, US04, US05).
 * O tipo (obrigatoria/optativa) fica registrado na matricula porque e o que limita
 * o aluno a 4 obrigatorias e 2 optativas por semestre.
 */
@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDisciplina tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatricula status = StatusMatricula.ATIVA;

    @Column(nullable = false)
    private LocalDateTime dataMatricula;

    private LocalDateTime dataCancelamento;

    protected Matricula() {
    }

    public Matricula(Aluno aluno, Disciplina disciplina, Semestre semestre, TipoDisciplina tipo) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.semestre = semestre;
        this.tipo = tipo;
        this.dataMatricula = LocalDateTime.now();
    }

    /**
     * Marca a matricula como cancelada, liberando a vaga na disciplina.
     */
    public void cancelar() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public boolean isAtiva() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public TipoDisciplina getTipo() {
        return tipo;
    }

    public void setTipo(TipoDisciplina tipo) {
        this.tipo = tipo;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }
}
