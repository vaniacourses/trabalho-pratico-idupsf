package com.upsf.backend.mapper;

import com.upsf.backend.dto.DisciplinaCursadaDTO;
import com.upsf.backend.dto.HistoricoDTO;
import com.upsf.backend.model.DisciplinaCursada;
import com.upsf.backend.model.Historico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricoMapper {

    @Mapping(source = "turma.disciplina.nome", target = "nomeDisciplina")
    @Mapping(source = "turma.disciplina.cod", target = "codigoDisciplina")
    @Mapping(source = "turma.disciplina.cargaHoraria", target = "cargaHoraria")
    @Mapping(source = "turma.cod", target = "codigoTurma")
    @Mapping(source = "turma.anoSemestre", target = "anoSemestre")
    DisciplinaCursadaDTO toDisciplinaDTO(DisciplinaCursada entidade);

    // @Mapping(source = "coeficienteRend", target = "coeficienteRendimento")
    @Mapping(source = "listaDisciplinas", target = "disciplinas")
    @Mapping(source = "discente.matricula", target = "matriculaAluno")
    @Mapping(source = "discente.nome", target = "nomeAluno")
    HistoricoDTO toDTO(Historico historico);
}