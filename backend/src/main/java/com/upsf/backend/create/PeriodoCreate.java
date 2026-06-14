package com.upsf.backend.create;

import java.time.LocalDateTime;

public record PeriodoCreate(
        String semestre,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        LocalDateTime dataInicioInscricao,
        LocalDateTime dataFimInscricao
) {}