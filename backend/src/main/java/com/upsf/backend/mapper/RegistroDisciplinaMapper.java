package com.upsf.backend.mapper;

import com.upsf.backend.dto.RegistroDisciplinaDTO;
import com.upsf.backend.model.RegistroDisciplina;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DisciplinaMapper.class)
public interface RegistroDisciplinaMapper {

    RegistroDisciplinaDTO toDTO(RegistroDisciplina registro);

    List<RegistroDisciplinaDTO> toDTOList(List<RegistroDisciplina> registros);

}