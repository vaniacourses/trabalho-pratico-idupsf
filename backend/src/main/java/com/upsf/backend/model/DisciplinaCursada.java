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
    private float notaVS;
    private String statusFinal; // pode ser enum
    private boolean frequencia;
    private String periodo;
    // também precisa de carga horaria pra realizar calculo do cr caso o calculo seja feito no modelo
    private int cargaHoraria;

    // status final é definido depois de disciplina cursada ser criada
    public DisciplinaCursada(Turma turma, float nota, float notaVS, boolean frequencia, String periodo, int cargaHoraria) {
        this.turma = turma;
        this.nota = nota;
        this.notaVS = notaVS;
        this.frequencia = frequencia;
        this.periodo = periodo;
        this.cargaHoraria = cargaHoraria;
    }

    public void calcularStatusFinal(){
        if(this.nota >= 6.0) setStatusFinal("APROVADO");
        else if(this.notaVS >= 6.0) setStatusFinal("APROVADO");
        else setStatusFinal("REPROVADO");
    }
}
