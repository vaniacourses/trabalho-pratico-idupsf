package com.upsf.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DisciplinaDTO(Long id,
                            String cod,
                            String nome)
{
}
