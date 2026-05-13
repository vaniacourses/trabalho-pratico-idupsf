package com.upsf.backend.mapper;

import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.model.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    @Mapping(source = "codigo", target = "codigoTurma")
    @Mapping(source = "disciplina.nome", target = "nomeDisciplina")
    @Mapping(source = "docente.nome", target = "nomeDocente")
    TurmaDTO toTurmaDTO(Turma turma); // Mapeamento de Turma para TurmaDTO

    // Mapeamento de List<Turma> para List<TurmaDTO>
    List<TurmaDTO> toTurmasDTO(List<Turma> turmas);

    @Mapping(source = "disciplinaResumo", target = "disciplina")
    @Mapping(source = "docenteResumo", target = "docente")
    Turma toTurma(TurmaDTO dto); // Mapeamento de TurmaDTO para Turma

    @Mapping(source = "disciplinaResumo", target = "disciplina")
    @Mapping(source = "docenteResumo", target = "docente")
    Turma toTurma(TurmaCreate produtoCreate); // Mapeamento de TurmaCreate para Turma
}
