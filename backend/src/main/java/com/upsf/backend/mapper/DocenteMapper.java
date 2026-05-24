package com.upsf.backend.mapper;

import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.dto.DocenteDTO;
import com.upsf.backend.model.Docente;
import com.upsf.backend.update.DocenteUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocenteMapper {
    @Mapping(source = "departamento.id", target = "idDepartamento")
    DocenteDTO toDto(Docente docente);
    Docente toEntity(DocenteCreate docenteCreate);
    Docente toEntity(DocenteUpdate docenteUpdate);
    List<DocenteDTO> toDtos(List<Docente> docentes);
}