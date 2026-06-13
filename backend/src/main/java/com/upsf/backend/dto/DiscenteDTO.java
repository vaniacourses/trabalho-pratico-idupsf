package com.upsf.backend.dto;

import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Usuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.Date;

public record DiscenteDTO(Long id,
                          String matricula,
                          String nome,
                          String email,
                          String emailInst,
                          String cpf,
                          String nomeSocial,
                          String cep,
                          String logradouro,
                          Usuario.Genero genero,
                          Date dataNasc,
                          Usuario.Status status,
                          CursoDTO curso,
                          Long historicoId,
                          String periodo,
                          LocalDate dataIngresso,
                          String codCurriculo,
                          Discente.SituacaoAcademica situacaoAcademica,
                          Discente.FormaPermanencia formaPermanencia)
{}
