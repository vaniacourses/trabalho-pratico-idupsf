package com.upsf.backend.dto;

import com.upsf.backend.model.DisciplinaCursada;

import java.time.LocalDate;

public record DisciplinaCursadaDTO(
        Long id,
        String nomeDisciplina,
        String codigoDisciplina,
        String codigoTurma,
        Float nota,
        Float notaVS,
        String statusFinal, // Ex: APROVADO, REPROVADO
        Integer cargaHoraria,
        String anoSemestre) { // ex: "2026.1"

}