package com.upsf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cod;
    private String nome;
    private int cargaHoraria;
    @ManyToMany
    @JoinTable(name = "disciplina_prerequisitos",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisito_id"))
    private List<Disciplina> preRequisitos;

    public Disciplina(String cod, String nome, int cargaHoraria) {
        this.cod = cod;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = new ArrayList<>();
    }

    public void adicionarPreRequisito(Disciplina disciplina) {
        this.preRequisitos.add(disciplina);
    }

    public void removerPreRequisito(Disciplina disciplina) {
        this.preRequisitos.remove(disciplina);
    }

}
