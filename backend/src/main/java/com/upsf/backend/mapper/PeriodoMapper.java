package com.upsf.backend.mapper;

import com.upsf.backend.dto.PeriodoCreate;
import com.upsf.backend.dto.PeriodoDTO;
import com.upsf.backend.model.Periodo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeriodoMapper {

    Periodo toPeriodo(PeriodoCreate periodoCreate);

    PeriodoDTO toPeriodoDTO(Periodo periodo);

    List<PeriodoDTO> toPeriodosDTO(List<Periodo> periodos);

}