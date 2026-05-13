package com.upsf.backend.dto;

public record DiscenteDTO(Long id,
                          String nome,
                          String matricula,
                          String cpf,
                          Long id_historico) {
}
