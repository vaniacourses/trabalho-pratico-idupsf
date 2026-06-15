package com.upsf.backend.repository;

import com.upsf.backend.model.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {
    boolean existsByCursoId(Long cursoId);

    @Query("SELECT i.discente FROM Inscricao i WHERE i.turma.id = :turmaId AND i.status = 'ATIVA'")
    List<Discente> findDiscentesByTurmaId(@Param("turmaId") Long turmaId);

}
