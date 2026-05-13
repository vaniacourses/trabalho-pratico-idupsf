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
    private String anoSemestre;

    /*
    * Interpretação do StatusTurma:
    * -> ATIVA: Com Docente associado
    * -> INATIVA: Sem Docente associado (Criada assim)
    * -> FECHADA: Fim do período e término da turma
    * */
    public enum StatusTurma{ATIVA, INATIVA, FECHADA}
    @Enumerated(EnumType.STRING)
    private StatusTurma status; // pode ser enum {ATIVA, INATIVA} OU ABERTA, FECHADA

    private String ementa;
    private int maxAlunos;

    @ManyToOne
    @JoinColumn(name = "disciplina_id" , foreignKey = @ForeignKey(name = "TURMA_DISCIPLINA_ID_FK"))
    private Disciplina disciplina;
    @ManyToOne
    private Docente docente;
    @ManyToOne
    private Horario horario;

    // Docente pode ser atribuído depois da criação
    public Turma(String cod, String anoSemestre, String ementa, int maxAlunos, Disciplina disciplina, Horario horario) {
        this.cod = cod;
        this.anoSemestre = anoSemestre;
        this.status = StatusTurma.INATIVA;
        this.ementa = ementa;
        this.maxAlunos = maxAlunos; // Colocar um valor padrão (Ex.: 60)?
        this.disciplina = disciplina;
        this.horario = horario;
    }

    // não é um setEmenta()?
    public void atualizarEmenta(String novaEmenta){}

    public void encerrarTurma(){}

    public boolean isLotada(){ return false;}

}
