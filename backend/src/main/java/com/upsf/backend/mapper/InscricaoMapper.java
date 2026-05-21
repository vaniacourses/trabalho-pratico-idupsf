package com.upsf.backend.mapper;

import com.upsf.backend.dto.InscricaoDTO;
import com.upsf.backend.model.Inscricao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InscricaoMapper{

//    @Mapping(source = "turma.codigo", target = "codigoTurma")
//    @Mapping(source = "turma.disciplina.nome", target = "nomeDisciplina")
//    @Mapping(source = "discente.matricula", target = "matriculaDiscente")
//    @Mapping(source = "discente.nome", target = "nomeDiscente")
//    InscricaoResponseDTO toResponseDTO(RegistroDisciplina inscricao);

    InscricaoDTO toResponseDTO(Inscricao inscricao);

    List<InscricaoDTO> toInscricoesDTO(List<Inscricao> inscricoes);
}