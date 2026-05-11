package com.upsf.backend.mapper;

import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.model.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    @Mapping(source = "codigo", target = "codigoTurma")
    @Mapping(source = "disciplina.nome", target = "nomeDisciplina")
    @Mapping(source = "docente.nome", target = "nomeDocente")
    TurmaDTO toDTO(Turma turma);

    List<TurmaDTO> toTurmasDto(List<Turma> turmas);

    Turma toEntity(TurmaDTO dto);
}
