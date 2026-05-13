package com.upsf.backend.resumo;

import com.upsf.backend.model.Docente;

public record DocenteResumo(
        String matricula,
        String nome,
        Docente.Titulacao titulacao
        )
{}
