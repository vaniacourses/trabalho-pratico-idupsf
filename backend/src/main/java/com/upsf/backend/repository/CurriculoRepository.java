package com.upsf.backend.repository;

import com.upsf.backend.model.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
    boolean existsByCod(String cod);

    // Garante que o currículo buscado pertence ao curso informado
    @Query("SELECT c FROM Curso curso JOIN curso.curriculos c WHERE c.id = :curriculoId AND curso.id = :cursoId")
    Optional<Curriculo> findByIdAndCursoId(@Param("curriculoId") Long curriculoId,
                                           @Param("cursoId") Long cursoId);
}