package com.upsf.backend.dto;

import com.upsf.backend.model.Usuario;

public record LoginResponseDTO(
        Long id,
        String matricula,
        String nome,
        String email,
        String emailInst,
        Usuario.Status status,
        String tipo
) {
}
