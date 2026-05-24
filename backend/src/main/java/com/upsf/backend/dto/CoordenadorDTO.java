package com.upsf.backend.dto;

import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record CoordenadorDTO(Long id,
                             String nome,
                             String matricula,
                             String cpf,
                             String email,
                             String emailInst,
                             Date dataNasc,
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