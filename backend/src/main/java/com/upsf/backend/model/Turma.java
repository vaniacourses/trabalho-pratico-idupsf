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
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cod;
    private String nome;
    private String anoSemestre;
    private String status;
    private String ementa;
    private int maxAlunos;
    @ManyToOne
    private Disciplina disciplina;
    @ManyToOne
    private Docente docente;
    @ManyToOne
    private Horario horario;

    // docente pode ser atribuido depois da criação
    public Turma(String cod, String nome, String anoSemestre, String status, String ementa, int maxAlunos, Disciplina disciplina, Horario horario) {
        this.cod = cod;
        this.nome = nome;
        this.anoSemestre = anoSemestre;
        this.status = status;
        this.ementa = ementa;
        this.maxAlunos = maxAlunos;
        this.disciplina = disciplina;
        this.horario = horario;
    }

    public void atualizarEmenta(String novaEmenta){}

    public void encerrarTurma(){}

    public boolean isLotada(){ return false;}

}
