package com.upsf.backend.dto;

import java.util.List;

public record HistoricoDTO(
        Long id,
        String matriculaAluno,
        String nomeAluno,
        Float coeficienteRend,
        List<DisciplinaCursadaDTO> disciplinas) {
}