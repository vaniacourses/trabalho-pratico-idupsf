package com.upsf.backend.mapper;

import com.upsf.backend.dto.DisciplinaCursadaDTO;
import com.upsf.backend.dto.HistoricoDTO;
import com.upsf.backend.model.DisciplinaCursada;
import com.upsf.backend.model.Historico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricoMapper {

    @Mapping(source = "disciplina.nome", target = "nomeDisciplina")
    @Mapping(source = "disciplina.codigo", target = "codigoDisciplina")
    @Mapping(source = "disciplina.cargaHoraria", target = "cargaHoraria")
    DisciplinaCursadaDTO toDisciplinaDTO(DisciplinaCursada entidade);

    @Mapping(source = "coeficienteRend", target = "coeficienteRendimento")
    HistoricoDTO toDTO(Historico historico);
}