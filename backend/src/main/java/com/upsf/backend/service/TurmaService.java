package com.upsf.backend.service;

import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaMapper turmaMapper;

    // Função de Busca de Turmas Ativas com Retorno em DTO para TurmaController
    public List<TurmaDTO> buscarTurmasAtivas() {

        List<Turma> turmas = turmaRepository.buscarTurmasAtivas();
        return turmaMapper.toTurmasDTO(turmas);
    }

    // Função de Busca de Turmas Ativas com Retorno em Entity e com Pré-Requisitos para InscricaoService
    public List<Turma> buscarTurmasEntitiesAtivasComRequisitos() {
        return turmaRepository.buscarTurmasAtivasComRequisitos();
    }

    public TurmaDTO buscarTurmaPorId(long id) {
        Turma turma = turmaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turma com ID = " + id + " não encontrada."
        ));

        // Retorno convertendo para ProdtuoDto
        return turmaMapper.toTurmaDTO(turma);
    }

    // Função de Criação/Cadastro de Turmas (Tarefa do Coordenador)
    public TurmaDTO cadastrarTurma(TurmaCreate turmaCreate) {
        Turma turma = turmaMapper.toTurma(turmaCreate);
        turma = turmaRepository.save(turma);

        // Retorno convertendo para TurmaDto
        return turmaMapper.toTurmaDTO(turma);
    }

    // Função de Alteração de Dados de Turmas (Tarefa do Coordenador)
    public TurmaDTO alterarTurma(@RequestBody TurmaDTO turmaDto) {
        Turma turma = turmaMapper.toTurma(turmaDto);
        turma = turmaRepository.save(turma);
        return turmaMapper.toTurmaDTO(turma);
    }

    public void removerTurmaPorId(long id) {
        buscarTurmaPorId(id);
        turmaRepository.deleteById(id);
    }
}