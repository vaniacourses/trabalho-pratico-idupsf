package com.upsf.backend.dto;

public record RelatorioRegularidadeDTO(
        String nome,
        String cpf,
        String matricula,
        String curso,
        String periodo
) {}