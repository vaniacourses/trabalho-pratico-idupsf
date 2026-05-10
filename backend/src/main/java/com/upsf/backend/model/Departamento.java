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
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cod;
    private String nome;
    private String endereco;
    private String campus;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "departamento_id")
    private List<Curso> cursos;

    // talvez departamentos não tenham endereço e campus definidos no momento de criação
    public Departamento(String cod, String nome, String endereco, String campus) {
        this.cod = cod;
        this.nome = nome;
        this.endereco = endereco;
        this.campus = campus;
        this.cursos = new ArrayList<>();
    }

    public void adicionarCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public void removerCurso(Curso curso) {
        this.cursos.remove(curso);
    }

}
