package com.upsf.backend.update;

import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Usuario;

import java.util.Date;

public record DiscenteUpdate(
        String nome,
        String email,
        Date dataNasc,
        Usuario.Status status,
        String periodo,
        Discente.SituacaoAcademica situacaoAcademica,
        Discente.FormaPermanencia formaPermanencia
) {
}
