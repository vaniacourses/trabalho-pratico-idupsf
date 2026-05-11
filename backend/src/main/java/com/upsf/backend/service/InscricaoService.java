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
import java.util.List;

@Service
public class InscricaoService {

    @Autowired
    private HistoricoService historicoService;
    @Autowired
    private TurmaService turmaService;
//    @Autowired
//    private DisciplinaService disciplinaService;
    @Autowired
    private InscricaoMapper inscricaoMapper;
    @Autowired
    private TurmaMapper turmaMapper;

    // usa o disciplina service para ir até o disciplina repository e pegar a lista de pre requisitos
//    public List<TurmaDTO> listarTurmasDisponiveis(Long discenteId) {
//        // 1. Verificar período (Stub que conversamos antes)
//        //validarPeriodoInscricaoAtivo();
//
//        List<Disciplina> concluidas = historicoService.buscarDisciplinasConcluidas(discenteId);
//        List<Turma> turmasAtivas = turmaService.buscarTurmasAtivas();
//
//        List<Turma> disponiveis = new ArrayList<>();
//        for (Turma turma : turmasAtivas) {
//            List<Disciplina> preRequisitos = disciplinaService.buscarPreRequisitos(turma.getDisciplina().getId());
//            if (concluidas.containsAll(preRequisitos)) {
//                disponiveis.add(turma);
//            }
//        }
//
//        return turmaMapper.toTurmasDto(disponiveis);
//    }

    // usa o padrão do jpa de lazy loading para pegar os pre requisitos na propria turma pois cada turma tem uma
    // disciplina que tem os pre requisitos
    @Transactional(readOnly = true)
    public List<TurmaDTO> listarTurmasDisponiveis(Long discenteId) {

        List<Disciplina> concluidas = historicoService.buscarDisciplinasConcluidas(discenteId);
        List<Turma> turmasAtivas = turmaService.buscarTurmasAtivas();

        List<Turma> disponiveis = new ArrayList<>();
        for (Turma turma : turmasAtivas) {
            // essa linha substitui o uso do disciplina service + repository
            List<Disciplina> requisitos = turma.getDisciplina().getPreRequisitos();
            if (concluidas.containsAll(requisitos)) disponiveis.add(turma);
        }

        return turmaMapper.toTurmasDto(disponiveis);
    }
}