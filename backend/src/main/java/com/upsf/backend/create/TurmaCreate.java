package com.upsf.backend.create;

import com.fasterxml.jackson.annotation.JsonProperty;

// Dependência de @Null ainda inexistente
public record TurmaCreate(
        String cod,
        String anoSemestre,
        // String ementa,
        int maxAlunos,

        @JsonProperty("disciplina")
        Long disciplinaId,

        @JsonProperty("docente")
        Long docenteId,

        @JsonProperty("horario")
        Long horarioId
        )
{}
