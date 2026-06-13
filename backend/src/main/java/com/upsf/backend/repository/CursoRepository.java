package com.upsf.backend.repository;

import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByCod(String cod);

    boolean existsByCod(String cod);

    @Query("SELECT new com.upsf.backend.dto.CursoDTO(c.id, c.cod, c.nome, null, null, null, c.turno) FROM Curso c ORDER BY c.nome")
    List<CursoDTO> findAllAsOptions();
}