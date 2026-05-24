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
        if (registroDisciplinas == null) return 0;
        return registroDisciplinas.stream()
                .mapToInt(r -> r.getDisciplina().getCargaHoraria())
                .sum();
    }

    public int calcularHorasObrigatorias() {
        if (registroDisciplinas == null) return 0;
        return registroDisciplinas.stream()
                .filter(r -> r.getTipoCategoria() == RegistroDisciplina.TipoCategoria.OBRIGATORIA)
                .mapToInt(r -> r.getDisciplina().getCargaHoraria())
                .sum();
    }

    public int calcularHorasOptativas() {
        if (registroDisciplinas == null) return 0;
        return registroDisciplinas.stream()
                .filter(r -> r.getTipoCategoria() == RegistroDisciplina.TipoCategoria.OPTATIVA
                        || r.getTipoCategoria() == RegistroDisciplina.TipoCategoria.ELETIVA)
                .mapToInt(r -> r.getDisciplina().getCargaHoraria())
                .sum();
    }

    public void adicionarDisciplina(RegistroDisciplina disciplina) {
        this.registroDisciplinas.add(disciplina);
    }

    public void removerDisciplina(RegistroDisciplina disciplina) {
        this.registroDisciplinas.remove(disciplina);
    }

}
