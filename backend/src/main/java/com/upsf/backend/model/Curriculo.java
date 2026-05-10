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
public class Curriculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cod;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curriculo_id")
    private List<RegistroDisciplina> registroDisciplinas;

    public Curriculo(String cod) {
        this.cod = cod;
        this.registroDisciplinas = new ArrayList<>();
    }

    public int calcularTotalHoras() {
        return 0;
    }

    public int calcularHorasOptativas(){
        return 0;
    }

    public int calcularHorasObrigatorias(){
        return 0;
    }

    public void adicionarDisciplina(RegistroDisciplina disciplina) {
        this.registroDisciplinas.add(disciplina);
    }

    public void removerDisciplina(RegistroDisciplina disciplina) {
        this.registroDisciplinas.remove(disciplina);
    }

}
