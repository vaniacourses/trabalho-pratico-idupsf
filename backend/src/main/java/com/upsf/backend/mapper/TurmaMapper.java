package com.upsf.backend.mapper;

import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.model.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    // o mapping daqui estava source disciplina.nome target nomeDisciplina
    // troquei para source disciplina target disciplinaResumo, porque estava dando erro
    @Mapping(source = "cod", target = "codigoTurma")
    @Mapping(source = "disciplina", target = "disciplinaResumo")
    @Mapping(source = "docente", target = "docenteResumo")
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
