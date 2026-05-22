package com.upsf.backend.dto;

import java.time.LocalDateTime;

public record PeriodoCreate(
        String semestre,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        LocalDateTime dataInicioInscricao,
        LocalDateTime dataFimInscricao
) {}