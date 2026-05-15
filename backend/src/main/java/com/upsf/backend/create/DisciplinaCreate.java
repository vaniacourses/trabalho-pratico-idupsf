package com.upsf.backend.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsf.backend.model.Disciplina;

import java.util.List;

public record DisciplinaCreate(String cod,
                               String nome,
                               int cargaHoraria,
                               @JsonProperty("preRequisitos")
                               List<Long> preRequisitosIds) {
}
