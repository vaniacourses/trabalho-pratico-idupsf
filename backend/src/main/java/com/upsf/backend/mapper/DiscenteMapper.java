package com.upsf.backend.mapper;


import com.upsf.backend.create.DiscenteCreate;
import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.model.Discente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscenteMapper {

    DiscenteDTO toDto(Discente discente);
    Discente toEntity(DiscenteCreate discenteCreate);
    Discente toEntity(Discente discente);
}
