package com.upsf.backend.mapper;

import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.model.Curso;
import com.upsf.backend.model.Curriculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    // @Mapping(target = "curriculosIds", source = "curriculos")
    CursoDTO toCursoDTO(Curso curso);

    @Mapping(target = "curriculos", ignore = true)
    Curso toCurso(CursoDTO cursoDTO);

    default List<Long> mapCurriculosToIds(List<Curriculo> curriculos) {
        if (curriculos == null) {
            return null;
        }

        return curriculos.stream()
                .map(Curriculo::getId)
                .toList();
    }
}
