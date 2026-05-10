package com.upsf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<String> diasDaSemana;
    private String horarioInicio;
    private String horarioFim;

    public Horario(List<String> diasDaSemana, String horarioInicio, String horarioFim) {
        this.diasDaSemana = diasDaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    // acho que isso devia ser definido na criação sem possibilidade de alterar
    public void adicionarDiaDaSemana(String dia) {
        this.diasDaSemana.add(dia);
    }
    public void removerDiaDaSemana(String dia) {
        this.diasDaSemana.remove(dia);
    }
}
