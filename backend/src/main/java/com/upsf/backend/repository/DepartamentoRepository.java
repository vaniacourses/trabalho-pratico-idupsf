package com.upsf.backend.repository;

import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {


        @Query("SELECT new com.upsf.backend.dto.DepartamentoDTO(d.id, d.cod, d.nome, null, null) FROM Departamento d ORDER BY d.nome")
        List<DepartamentoDTO> findAllAsOptions();

}
