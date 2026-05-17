package com.upsf.backend.dto;

import com.upsf.backend.model.Curso;
import com.upsf.backend.model.Departamento;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record CoordenadorDTO(String id,
                             String nome,
                             String matricula,
                             String cpf,
                             Usuario.Status status,
                             Docente.Titulacao titulacao,
                             Docente.Regime regime,
                             Long idDepartamento,
                             List<String> areasAtuacao,
                             String lattes,
                             LocalDate dataIngresso,
                             Long idCurso,
                             Date inicioMandato,
                             Date fimMandato
                             ){

}