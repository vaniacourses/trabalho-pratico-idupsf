package com.upsf.backend.dto;

import java.time.LocalDate;

public record DisciplinaCursadaDTO(
        Long id,
        String nomeDisciplina,
        String codigoDisciplina,
        Float nota,
        Float notaVS,
        String status, // Ex: APROVADO, REPROVADO
        Integer cargaHoraria,
        String anoSemestre) { // ex: "2026.1"

}