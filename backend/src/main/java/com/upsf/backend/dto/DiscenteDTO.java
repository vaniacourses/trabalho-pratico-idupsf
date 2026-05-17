package com.upsf.backend.dto;

import com.upsf.backend.model.Curso;
import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Historico;
import com.upsf.backend.model.Usuario;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.Date;

public record DiscenteDTO(String matricula,
                          String nome,
                          String email,
                          String emailInst,
                          String cpf,
                          Date dataNasc,
                          Usuario.Status status,
                          Long cursoId,
                          Long historicoId,
                          String periodo,
                          LocalDate dataIngresso,
                          String codCurriculo,
                          Discente.SituacaoAcademica situacaoAcademica,
                          Discente.FormaPermanencia formaPermanencia) {
}
