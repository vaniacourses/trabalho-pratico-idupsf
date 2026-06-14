package com.upsf.backend.dto;

public record LoginRequestDTO(
        String cpf,
        String senha
) {
}
