package com.upsf.backend.mapper;

import com.upsf.backend.create.CursoCreate;
import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.model.Curso;
import com.upsf.backend.model.Curriculo;
import com.upsf.backend.resumo.CurriculoResumo;
import java.util.Collections;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "curriculos", source = "curriculos")
    CursoDTO toCursoDTO(Curso curso);

    @Mapping(target = "curriculos", ignore = true)
    @Mapping(target = "id", ignore = true)
    Curso toCurso(CursoDTO cursoDTO);

    @Mapping(target = "curriculos", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codCurriculoAtual", ignore = true)
    Curso toCurso(CursoCreate cursoCreate);

    default List<CurriculoResumo> mapCurriculos(List<Curriculo> curriculos) {
        if (curriculos == null) return Collections.emptyList();
        return curriculos.stream()
                .map(c -> new CurriculoResumo(c.getId(), c.getCod()))
                .toList();
    }
}