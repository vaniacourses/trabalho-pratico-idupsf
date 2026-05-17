package com.upsf.backend.dto;

import java.time.LocalDateTime;
//inscricaoDTO
public record InscricaoResponseDTO(
        Long id,
        String matriculaDiscente,
        String nomeDiscente,
        String nomeDisciplina,
        String codigoTurma,
        String status,
        LocalDateTime dataHoraInscricao) {
}