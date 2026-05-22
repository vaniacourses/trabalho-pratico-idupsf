package com.upsf.backend.mapper;

import com.upsf.backend.dto.InscricaoDTO;
import com.upsf.backend.model.Inscricao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InscricaoMapper{
    @Mapping(source = "discente.matricula", target = "matriculaDiscente")
    @Mapping(source = "discente.nome", target = "nomeDiscente")
    @Mapping(source = "turma.disciplina.nome", target = "nomeDisciplina")
    @Mapping(source = "turma.cod", target = "codigoTurma")
    InscricaoDTO toDTO(Inscricao inscricao);

    List<InscricaoDTO> toInscricoesDTO(List<Inscricao> inscricoes);
}