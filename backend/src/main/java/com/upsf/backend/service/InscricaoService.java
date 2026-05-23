package com.upsf.backend.service;

import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.mapper.InscricaoMapper;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.Inscricao;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private TurmaMapper turmaMapper;

    @Autowired
    private InscricaoMapper inscricaoMapper;

    @Transactional(readOnly = true)
    public List<TurmaDTO> listarTurmasDisponiveis(Long discenteId) {
        Set<Disciplina> concluidas = new HashSet<>(historicoService.buscarDisciplinasEntitiesAprovadas(discenteId));
        List<Turma> turmasAtivas = turmaService.buscarTurmasEntitiesAtivasComRequisitos();

        List<Turma> disponiveis = turmasAtivas.stream()
                .filter(turma -> concluidas.containsAll(turma.getDisciplina().getPreRequisitos()))
                .toList();

        return turmaMapper.toTurmasDTO(disponiveis);
    }

    public List<Inscricao> listarInscricoesPorTurma(Long id) {
        return inscricaoRepository.findByTurmaId(id);
    }

    public void deletarInscricoesPorTurma(Long turmaId) {
        inscricaoRepository.deleteByTurmaId(turmaId);
    }
}