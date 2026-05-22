package com.upsf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class DisciplinaCursada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Turma turma;
    private float nota;
    @Column(name = "nota_vs")
    private float notaVS;
    public enum StatusFinal{APROVADO, REPROVADO, TRANCADO, AGUARDO}
    @Enumerated(EnumType.STRING)
    @Column(name = "status_final")
    private StatusFinal statusFinal; // pode ser enum
    private boolean frequencia;
    private String periodo;
    // também precisa de carga horaria pra realizar calculo do cr caso o calculo seja feito no modelo
    @Column(name = "carga_horaria")
    private int cargaHoraria;

    // status final é definido depois de disciplina cursada ser criada
    public DisciplinaCursada(Turma turma, float nota, float notaVS, boolean frequencia, String periodo, int cargaHoraria) {
        this.turma = turma;
        this.nota = nota;
        this.notaVS = notaVS;
        this.frequencia = frequencia;
        this.periodo = periodo;
        this.cargaHoraria = cargaHoraria;
        this.statusFinal = StatusFinal.AGUARDO;
    }

    public void calcularStatusFinal(){
        if (this.frequencia && this.statusFinal != StatusFinal.TRANCADO) {
            if(this.nota >= 6.0) this.statusFinal = StatusFinal.APROVADO;
            else if(this.notaVS >= 6.0) this.statusFinal = StatusFinal.APROVADO;
            else this.statusFinal = StatusFinal.REPROVADO;
        }
        else {
            this.statusFinal = StatusFinal.REPROVADO;
        }
    }
}
