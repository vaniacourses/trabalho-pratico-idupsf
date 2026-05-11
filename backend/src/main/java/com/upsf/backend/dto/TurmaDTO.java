package com.upsf.backend.dto;

public record TurmaDTO(
        Long id,
        String codigoTurma,
        String turno,
        String nomeDisciplina,
        String nomeDocente,
        Integer vagasDisponiveis) {
}