package com.upsf.backend.repository;

import com.upsf.backend.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Busca turmas pelo status (ex: "ATIVA", "ABERTA")
    List<Turma> findByStatusIgnoreCase(String status);
}