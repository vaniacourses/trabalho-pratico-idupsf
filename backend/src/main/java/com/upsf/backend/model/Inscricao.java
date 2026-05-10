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
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Turma turma;
    @ManyToOne
    private Discente aluno;
    private String status; // Cancelada, Aprovada, Trancada, Em Espera?
    private float nota;
    private float notaVS;
    private boolean frequencia;

    // nota, notaVS e frequencia são definidos com o tempo
    public Inscricao(Turma turma, Discente aluno, String status) {
        this.turma = turma;
        this.aluno = aluno;
        this.status = status;
    }

    public void cancelar(){}
    public void efetivarExcedente(){}
}
