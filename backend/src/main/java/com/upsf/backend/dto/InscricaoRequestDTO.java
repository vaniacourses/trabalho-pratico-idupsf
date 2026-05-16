package com.upsf.backend.dto;

import java.util.List;

// tentei usar uma anotação notnull e não funcionou
// talvez deve ter matricula e não id
//inscricaoCreate
public record InscricaoRequestDTO(
        Long discenteId,
        List<Long> turmasIds) {
}
