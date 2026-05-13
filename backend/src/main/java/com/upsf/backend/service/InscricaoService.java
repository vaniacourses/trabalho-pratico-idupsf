package com.upsf.backend.service;

import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.mapper.InscricaoMapper;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.Turma;
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
    private HistoricoService historicoService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private TurmaMapper turmaMapper;

    @Autowired
    private InscricaoMapper inscricaoMapper;

    // usa o padrão do jpa de lazy loading para pegar os pre requisitos na propria turma pois cada turma tem uma
    // disciplina que tem os pre requisitos
//    public List<TurmaDTO> listarTurmasDisponiveis(Long discenteId) {
//
//        List<Disciplina> concluidas = historicoService.buscarDisciplinasConcluidas(discenteId);
//        List<Turma> turmasAtivas = turmaService.buscarTurmasEntitiesComRequisitosAtivas();
//
//        List<Turma> disponiveis = new ArrayList<>();
//        for (Turma turma : turmasAtivas) {
//            // essa linha substitui o uso do disciplina service + repository
//            List<Disciplina> requisitos = turma.getDisciplina().getPreRequisitos();
//            if (concluidas.containsAll(requisitos)) disponiveis.add(turma);
//        }
//
//        return turmaMapper.toTurmasDto(disponiveis);
//    }

    @Transactional(readOnly = true)
    public List<TurmaDTO> listarTurmasDisponiveis(Long discenteId) {
        Set<Disciplina> concluidas = new HashSet<>(historicoService.buscarDisciplinasConcluidas(discenteId));
        List<Turma> turmasAtivas = turmaService.buscarTurmasEntitiesAtivasComRequisitos();

        List<Turma> disponiveis = turmasAtivas.stream()
                .filter(turma -> concluidas.containsAll(turma.getDisciplina().getPreRequisitos()))
                .toList();

        return turmaMapper.toTurmasDTO(disponiveis);
    }
}