package com.upsf.backend.dto;

import java.util.List;

public record CurriculoDTO(
        Long id,
        String cod,
        int totalHoras,
        int horasObrigatorias,
        int horasOptativas,
        Long cursoId,
        List<RegistroDisciplinaDTO> disciplinas
)
{}
