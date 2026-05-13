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
@ToString
@Entity
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float coeficienteRend;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "historico_id")
    private List<DisciplinaCursada> listaDisciplinas;
    @OneToOne
    @JoinColumn(name = "discente_id")
    private Discente discente;

    //cr inicial 0
    public Historico() {
        this.coeficienteRend = 0;
        this.listaDisciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(DisciplinaCursada disciplina){
        this.listaDisciplinas.add(disciplina);
    }

    public void removerDisciplina(DisciplinaCursada disciplina){
        this.listaDisciplinas.remove(disciplina);
    }

    // cr só é calculado no fim do periodo
    public float calcularCR(){
        if(this.listaDisciplinas == null || this.listaDisciplinas.isEmpty()) return 0f;

        float somaNotasPonderadas = 0;
        int somaCargaHoraria = 0;

        for(DisciplinaCursada disciplina: this.listaDisciplinas) {
            // disciplina é instanciada ja com nota e notavs então se nota é >= 6 então notavs não é contabilizada
            if(disciplina.getNota() >= 6.0)
                somaNotasPonderadas += disciplina.getNota()*disciplina.getCargaHoraria();
            else somaNotasPonderadas += disciplina.getNotaVS()*disciplina.getCargaHoraria();

            somaCargaHoraria += disciplina.getCargaHoraria();
        }

        this.coeficienteRend = somaNotasPonderadas/somaCargaHoraria;
        return this.coeficienteRend;
    }
}
