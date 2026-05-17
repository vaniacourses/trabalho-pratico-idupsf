package com.upsf.backend.repository;

import com.upsf.backend.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByCod(String cod);

    boolean existsByCod(String cod);
}