package com.upsf.backend.dto;

import java.util.List;

public record RelatorioHistoricoDTO(
        String nome,
        String cpf,
        String matricula,
        Float cr,
        List<DisciplinaCursadaDTO> disciplinasCursadas) {
}