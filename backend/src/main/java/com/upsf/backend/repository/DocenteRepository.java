package com.upsf.backend.repository;

import com.upsf.backend.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    @Query("SELECT d FROM Docente d WHERE d.departamento IN (SELECT dep FROM Departamento dep JOIN dep.cursos c WHERE c.id = :cursoId)")
    List<Docente> findDocentesByDepartamentoDoCurso(@Param("cursoId") Long cursoId);
}