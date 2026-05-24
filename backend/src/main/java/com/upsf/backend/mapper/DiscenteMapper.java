package com.upsf.backend.mapper;


import com.upsf.backend.create.DiscenteCreate;
import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.model.Discente;
import com.upsf.backend.update.DiscenteUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscenteMapper {
    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "historico.id", target = "historicoId")
    DiscenteDTO toDto(Discente discente);
    Discente toEntity(DiscenteCreate discenteCreate);
    Discente toEntity(DiscenteUpdate discenteUpdate);
    List<DiscenteDTO> toDtos(List<Discente> discentes);
}
