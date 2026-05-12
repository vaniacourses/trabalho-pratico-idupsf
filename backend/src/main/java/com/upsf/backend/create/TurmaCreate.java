package com.upsf.backend.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Horario;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

// Dependência de @Null ainda inexistente
public record TurmaCreate(Long id,
                          String cod,
                          String nome,
                          String anoSemestre,
                          String ementa,
                          int maxAlunos,
                          @JsonProperty("disciplina")
                          Disciplina disciplina,
                          @JsonProperty("docente")
                          Docente docente,
                          @JsonProperty("horário")
                          Horario horario
                          )
{}
