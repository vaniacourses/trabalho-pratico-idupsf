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
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cod;
    private String nome;
    private int duracaoMin;
    private int duracaoMax;
    private String codCurriculoAtual;
    public enum Turno{DIURNO, NOTURNO, INTEGRAL}
    @Enumerated(EnumType.STRING)
    private Turno turno;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    @OneToMany(mappedBy ="curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curriculo> curriculos = new ArrayList<>();

    // Construtor sem codCurriculoAtual, tem que chamar o setCodCurriculoAtual separadamente para definir
    public Curso(String cod, String nome, int duracaoMin, int duracaoMax, Turno turno) {
        this.cod = cod;
        this.nome = nome;
        this.duracaoMin = duracaoMin;
        this.duracaoMax = duracaoMax;
        this.turno = turno;
        this.curriculos = new ArrayList<>();
    }

    public void adicionarCurriculo(Curriculo curriculo) {
        this.curriculos.add(curriculo);
    }

    public void removerCurriculo(Curriculo curriculo) {
        this.curriculos.removeIf(c -> c.getId().equals(curriculo.getId()));
    }

}
