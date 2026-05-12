package com.upsf.backend.service;

import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return turmaMapper.toTurmasDto(turmas);
    }

    // Função de Busca de Turmas Ativas com Retorno em Entity e com Pré-Requisitos para InscricaoService
    public List<Turma> buscarTurmasEntitiesAtivasComRequisitos() {
        return turmaRepository.buscarTurmasAtivasComRequisitos();
    }

    // Ainda a implementar - Cláudio
    public void cadastrarTurma(TurmaCreate turmaCreate) {
    }
}