package com.upsf.backend.mapper;

import com.upsf.backend.create.CoordenadorCreate;
import com.upsf.backend.update.CoordenadorUpdate;
import org.mapstruct.Mapper;

import com.upsf.backend.model.Coordenador;
import com.upsf.backend.dto.CoordenadorDTO;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper{
    @Mapping(source = "departamento.id", target = "idDepartamento")
    @Mapping(source = "curso.id", target = "idCurso")
    CoordenadorDTO toDto(Coordenador coordenador);
    Coordenador toEntity(CoordenadorCreate coordenadorCreate);
    Coordenador toEntity(CoordenadorUpdate coordenadorUpdate);
    List<CoordenadorDTO> toDtos(List<Coordenador> coordenadores);
}