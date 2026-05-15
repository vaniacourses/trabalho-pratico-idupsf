package com.upsf.backend.mapper;

import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.dto.DocenteDTO;
import com.upsf.backend.model.Docente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocenteMapper {
    DocenteDTO toDto(Docente docente);
    Docente toEntity(DocenteCreate docenteCreate);
}