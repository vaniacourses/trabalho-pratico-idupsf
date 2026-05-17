package com.upsf.backend.create;

import com.upsf.backend.model.Curso;
import java.util.List;

public record CursoCreate(String cod,
                          String nome,
                          int duracaoMin,
                          int duracaoMax,
                          String codCurriculoAtual,
                          Curso.Turno turno,
                          List<Long> curriculosIds) {
}
