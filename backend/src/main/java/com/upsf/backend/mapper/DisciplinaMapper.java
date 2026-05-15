package com.upsf.backend.mapper;

import com.upsf.backend.create.DisciplinaCreate;
import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.model.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    Disciplina toDisciplina(DisciplinaDTO disciplinaDTO);

    @Mapping(target = "preRequisitos", ignore = true)
    Disciplina toDisciplina(DisciplinaCreate disciplinaCreate);

    DisciplinaDTO toDisciplinaDTO(Disciplina disciplina);

    List<DisciplinaDTO> toDisciplinasDTO(List<Disciplina> disciplinas);

}
