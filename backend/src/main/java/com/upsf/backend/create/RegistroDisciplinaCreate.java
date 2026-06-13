package com.upsf.backend.create;

import com.upsf.backend.model.RegistroDisciplina.TipoCategoria;

public record RegistroDisciplinaCreate(
        Long disciplinaId,
        TipoCategoria tipoCategoria,
        int periodoRecomendado
) {}
