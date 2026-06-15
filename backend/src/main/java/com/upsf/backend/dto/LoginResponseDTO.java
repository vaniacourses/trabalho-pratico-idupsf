package com.upsf.backend.dto;

public record LoginResponseDTO(
        Long id,
        String perfil,
        String token
) {
}
