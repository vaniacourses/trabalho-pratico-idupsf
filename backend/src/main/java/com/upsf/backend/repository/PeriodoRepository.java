package com.upsf.backend.repository;

import com.upsf.backend.model.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    // Busca o periodo inscrição atual, é optional pq existem ferias
    @Query("select p from Periodo p where :agora between p.dataInicioInscricao and p.dataFimInscricao")
    Optional<Periodo> findPeriodoInscricaoAtual(LocalDateTime agora);

    //Verifica se já existe um periodo com aquele semestre
    boolean existsBySemestre(String semestre);
}
