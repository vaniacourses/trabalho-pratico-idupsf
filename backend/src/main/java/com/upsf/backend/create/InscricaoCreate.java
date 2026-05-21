package com.upsf.backend.create;

import java.util.List;

// tentei usar uma anotação notnull e não funcionou
// talvez deve ter matricula e não id
//inscricaoCreate
public record InscricaoCreate(
        Long discenteId,
        List<Long> turmasIds) {
}
