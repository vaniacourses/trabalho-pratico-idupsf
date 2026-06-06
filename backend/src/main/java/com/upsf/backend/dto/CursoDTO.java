package com.upsf.backend.dto;

import com.upsf.backend.model.Curso;
//import com.upsf.backend.resumo.CurriculoResumo;

import java.util.List;

public record CursoDTO(
        Long id,
        String cod,
        String nome,
        Integer duracaoMin,
        Integer duracaoMax,
        String codCurriculoAtual,
        Curso.Turno turno
        // List<CurriculoResumo> curriculos
        )
{}