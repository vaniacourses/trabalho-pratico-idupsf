package com.upsf.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsf.backend.model.Disciplina;

import java.util.List;

public record DisciplinaDTO(Long id,
                            Long departamento_id,
                            String cod,
                            String nome,
                            Integer cargaHoraria,
                            Disciplina.Status status)
{
}
