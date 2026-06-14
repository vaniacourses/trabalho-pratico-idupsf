package com.upsf.backend.dto;

public record InscricaoResponseDTO(
        Long id,
        String nomeAluno,
        String matriculaAluno,
        Float nota,
        Float notaVS,
        Boolean frequencia,
        String status
) {}