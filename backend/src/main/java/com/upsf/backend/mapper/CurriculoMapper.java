package com.upsf.backend.mapper;

import com.upsf.backend.dto.CurriculoDTO;
import com.upsf.backend.model.Curriculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurriculoMapper {

    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "totalHoras",
            expression = "java(curriculo.calcularTotalHoras())")
    @Mapping(target = "horasObrigatorias",
            expression = "java(curriculo.calcularHorasObrigatorias())")
    @Mapping(target = "horasOptativas",
            expression = "java(curriculo.calcularHorasOptativas())")
    CurriculoDTO toDTO(Curriculo curriculo);
}