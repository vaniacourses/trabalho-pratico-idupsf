package com.upsf.backend.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Horario;
import com.upsf.backend.resumo.DisciplinaResumo;
import com.upsf.backend.resumo.DocenteResumo;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

// Dependência de @Null ainda inexistente
public record TurmaCreate(
        String cod,
        String nome,
        String anoSemestre,
        String ementa,
        int maxAlunos,

        @JsonProperty("disciplina")
        DisciplinaResumo disciplinaResumo,

        @JsonProperty("docente")
        DocenteResumo docenteResumo,

        @JsonProperty("horário")
        Horario horario
        )
{}
