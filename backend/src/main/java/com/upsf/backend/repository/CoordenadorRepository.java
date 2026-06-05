package com.upsf.backend.repository;

import com.upsf.backend.model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
    boolean existsByCursoId(Long cursoId);



}