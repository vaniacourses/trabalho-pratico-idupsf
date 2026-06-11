package com.upsf.backend.repository;

import com.upsf.backend.model.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
    boolean existsByCod(String cod);

    Optional<Curriculo> findByIdAndCursoId(
            Long curriculoId,
            Long cursoId);

    List<Curriculo> findAllByCursoId(Long cursoId);
}