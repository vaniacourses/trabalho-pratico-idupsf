package com.upsf.backend.dto;

import com.upsf.backend.model.RegistroDisciplina.TipoCategoria;

public record RegistroDisciplinaDTO(

    Long id,
    TipoCategoria tipoCategoria,
    int periodoRecomendado,
    Long disciplinaId,
    String disciplinaCod,
    String disciplinaNome,
    int cargaHoraria
)
{}