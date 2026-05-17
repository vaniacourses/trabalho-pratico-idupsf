package com.upsf.backend.dto;

import com.upsf.backend.model.Curso;

import java.util.List;

public record CursoDTO(
        Long id,
        String cod,
        String nome,
        int duracaoMin,
        int duracaoMax,
        String codCurriculoAtual,
        Curso.Turno turno,
        List<Long> curriculosIds){
}