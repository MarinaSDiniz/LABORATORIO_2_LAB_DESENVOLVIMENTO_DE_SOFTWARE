package br.pucminas.matriculas.model;

import br.pucminas.matriculas.model.enums.StatusSemestre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;

/**
 * Semestre letivo e seu periodo de matriculas (US05, US06).
 * Matricula e cancelamento so sao permitidos entre inicioMatriculas e fimMatriculas.
 */
@Entity
@Table(name = "semestre", uniqueConstraints = @UniqueConstraint(columnNames = {"ano", "periodo"}))
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int ano;

    /** 1 para o primeiro semestre, 2 para o segundo. */
    @Column(nullable = false)
    private int periodo;

    @Column(nullable = false)
    private LocalDate inicioMatriculas;

    @Column(nullable = false)
    private LocalDate fimMatriculas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSemestre status = StatusSemestre.PLANEJADO;

    protected Semestre() {
    }

    public Semestre(int ano, int periodo, LocalDate inicioMatriculas, LocalDate fimMatriculas) {
        this.ano = ano;
        this.periodo = periodo;
        this.inicioMatriculas = inicioMatriculas;
        this.fimMatriculas = fimMatriculas;
    }

    /**
     * Indica se a data informada esta dentro do periodo de matriculas deste semestre.
     */
    public boolean periodoMatriculaAberto(LocalDate data) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public LocalDate getInicioMatriculas() {
        return inicioMatriculas;
    }

    public void setInicioMatriculas(LocalDate inicioMatriculas) {
        this.inicioMatriculas = inicioMatriculas;
    }

    public LocalDate getFimMatriculas() {
        return fimMatriculas;
    }

    public void setFimMatriculas(LocalDate fimMatriculas) {
        this.fimMatriculas = fimMatriculas;
    }

    public StatusSemestre getStatus() {
        return status;
    }

    public void setStatus(StatusSemestre status) {
        this.status = status;
    }
}
