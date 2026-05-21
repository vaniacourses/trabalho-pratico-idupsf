package com.upsf.backend.repository;

import com.upsf.backend.model.Periodo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PeriodoRepository {
    // Busca o periodo inscrição atual, é optional pq existem ferias
    @Query("select p from Periodo p where :agora between p.dataInicioInscricao and p.dataFimInscricao")
    Optional<Periodo> findPeriodoInscricaoAtual(LocalDateTime agora);
}
