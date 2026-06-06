package com.upsf.backend.dto;

import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record DocenteDTO(Long id,
                         String nome,
                         String matricula,
                         String cpf,
                         String nomeSocial,
                         String cep,
                         String logradouro,
                         Usuario.Genero genero,
                         String email,
                         String emailInst,
                         Date dataNasc,
                         Usuario.Status status,
                         Docente.Titulacao titulacao,
                         Docente.Regime regime,
                         DepartamentoDTO departamento,
                         List<String> areasAtuacao,
                         String lattes,
                         LocalDate dataIngresso){

}