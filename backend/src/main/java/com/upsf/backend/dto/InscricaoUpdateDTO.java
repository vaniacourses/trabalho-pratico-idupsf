package com.upsf.backend.dto;

public record InscricaoUpdateDTO(
        Long inscricaoId,
        Float nota,
        Float notaVS,
        Boolean frequencia
) {}