package com.upsf.backend.dto;

// tentei usar uma anotação notnull e não funcionou
// talvez deve ter matricula e não id
public record InscricaoRequestDTO(
        Long discenteId,
        Long turmaId) {
}
