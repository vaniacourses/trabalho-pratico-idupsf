package com.upsf.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsf.backend.model.Horario;
import com.upsf.backend.model.Turma;

public record TurmaDTO(
        Long id,
        String cod,
        Horario horario,
        Turma.StatusTurma status,
        String anoSemestre,

        DisciplinaDTO disciplina,

        DocenteDTO docente,

        Integer maxAlunos
        )
{}