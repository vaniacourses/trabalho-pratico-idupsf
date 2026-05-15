package com.upsf.backend.mapper;

import org.mapstruct.Mapper;

import com.upsf.backend.model.Coordenador;
import com.upsf.backend.dto.CoordenadorDTO;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper{
    CoordenadorDTO toDto(Coordenador coordenador);
}