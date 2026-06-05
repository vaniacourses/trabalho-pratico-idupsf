package com.upsf.backend.dto;

import com.upsf.backend.model.RegistroDisciplina;

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
