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
    @JoinColumn(name = "turma_id")
    private Turma turma;
    @ManyToOne
    @JoinColumn(name = "discente_id")
    private Discente discente;
    private String status; // Cancelada, Aprovada, Trancada, Em Espera?
    private Float nota;
    @Column(name = "nota_vs")
    private Float notaVS;
    private Boolean frequencia;

    // nota, notaVS e frequencia são definidos com o tempo
    public Inscricao(Turma turma, Discente discente, String status) {
        this.turma = turma;
        this.discente = discente;
        this.status = status;
    }

    public void trancar() {
        this.status = "Trancada";
    }
    public void efetivarExcedente(){}
}
