package com.upsf.backend.update;

import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;

import java.util.Date;
import java.util.List;

public record DocenteUpdate(
        String nome,
        String email,
        Date dataNasc,
        Usuario.Status status,
        Docente.Titulacao titulacao,
        Docente.Regime regime,
        List<String> areasAtuacao,
        String lattes
) {
}
