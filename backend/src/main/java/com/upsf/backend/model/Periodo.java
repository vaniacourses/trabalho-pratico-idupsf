package com.upsf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
// A classe vai ter que ser instanciada pelo coordenador, talvez seja necessário um administrador
// não queremos coordenadores diferentes com esse poder, apenas uma pessoa deveria ter esse poder
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String semestre; // "2026.1", "2024.2"
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    // Periodo de inscrição
    private LocalDateTime dataInicioInscricao;
    private LocalDateTime dataFimInscricao;
    @OneToMany(mappedBy = "periodo")
    private List<Turma> turmas;

    // o calendario é definido pela reitoria no inicio do periodo, todas as datas devem existir ao instanciar a classe
    public Periodo(String semestre, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataInicioInscricao,
                   LocalDateTime dataFimInscricao) {
        this.semestre = semestre;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataInicioInscricao = dataInicioInscricao;
        this.dataFimInscricao = dataFimInscricao;
        this.turmas = new ArrayList<>();
    }

    public boolean isInscricaoAtiva() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(dataInicioInscricao) && agora.isBefore(dataFimInscricao);
    }

    public void adicionarTurmas(Turma turma) {
        this.turmas.add(turma);
    }

    public void removerTurmas(Turma turma) {
        this.turmas.remove(turma);
    }
}