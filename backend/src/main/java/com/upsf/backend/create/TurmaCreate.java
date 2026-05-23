package com.upsf.backend.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Horario;
import com.upsf.backend.resumo.DocenteResumo;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

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
