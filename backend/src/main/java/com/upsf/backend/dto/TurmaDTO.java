package com.upsf.backend.dto;

import com.upsf.backend.model.Horario;
import com.upsf.backend.resumo.DisciplinaResumo;
import com.upsf.backend.resumo.DocenteResumo;

public record TurmaDTO(
        Long id,
        String codigoTurma,
        Horario horario,
        DisciplinaResumo disciplinaResumo,
        DocenteResumo docenteResumo,
        Integer vagasDisponiveis) {
}