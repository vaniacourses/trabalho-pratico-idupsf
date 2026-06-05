package com.upsf.backend.repository;

import com.upsf.backend.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByCod(String cod);

    List<Curso> findAllByDepartamentoId(Long departamentoId);

    Optional<Curso> findByIdAndDepartamentoId(Long cursoId, Long departamentoId);

    boolean existsByCod(String cod);
}