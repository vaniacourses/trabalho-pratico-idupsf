package com.upsf.backend.dto;

import java.time.LocalDate;

public record DisciplinaCursadaDTO(
        Long id,
        String nomeDisciplina,
        String codigoDisciplina,
        Float nota,
        String status, // Ex: APROVADO, REPROVADO
        Integer cargaHoraria) {

}