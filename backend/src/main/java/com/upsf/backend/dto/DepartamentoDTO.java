package com.upsf.backend.dto;

public record DepartamentoDTO(
        Long id,
        String cod,
        String nome,
        String endereco,
        String campus
) {
}
