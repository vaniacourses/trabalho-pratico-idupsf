package com.upsf.backend.create;

import com.upsf.backend.model.Curso;

public record CursoCreate(String cod,
                          String nome,
                          int duracaoMin,
                          int duracaoMax,
                          Curso.Turno turno) {
}