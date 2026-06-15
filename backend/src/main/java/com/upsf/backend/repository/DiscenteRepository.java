package com.upsf.backend.repository;

import com.upsf.backend.model.Discente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {
    boolean existsByCursoId(Long cursoId);
}
