package com.upsf.backend.service;

import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.dto.InscricaoRequestDTO;
import com.upsf.backend.dto.InscricaoResponseDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.InscricaoMapper;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.Inscricao;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private TurmaRepository turmaRepository;

    @Autowired
    private InscricaoMapper inscricaoMapper;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private PeriodoRepository periodoRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    // talvez eu tenha introduzido um erro aqui
    // Função de Listagem de Turmas Disponíveis para um determinado Discente
    // Em resposta à InscricaoController
    @Transactional(readOnly = true)
    public List<TurmaDTO> listarTurmasDisponiveis(Long discenteId) {
        Set<Disciplina> concluidas = new HashSet<>(historicoService.buscarDisciplinasEntitiesAprovadas(discenteId));
        List<Turma> turmasAtivas = turmaService.buscarTurmasEntitiesAtivasComRequisitos();

        List<Turma> disponiveis = turmasAtivas.stream()
                .filter(turma -> concluidas.containsAll(turma.getDisciplina().getPreRequisitos()))
                .toList();

        return turmaMapper.toTurmasDTO(disponiveis);
    }

    @Transactional
    public List<InscricaoResponseDTO> realizarInscricao(InscricaoRequestDTO dto) {
        // Garantia de período ativo antes de processar
        validarPeriodoInscricaoAtivo();

        Discente discente = discenteRepository.findById(dto.discenteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Discente não encontrado"));

        List<Turma> turmasSelecionadas = turmaRepository.findAllById(dto.turmasIds());

        // Validação de conflito de horários (Entre as novas e com as já existentes)
        validarConfrontoHorarios(discente, turmasSelecionadas);

        List<Inscricao> novasInscricoes = new ArrayList<>();
        for (Turma turma : turmasSelecionadas) {
            Inscricao inscricao = new Inscricao();
            inscricao.setDiscente(discente);
            inscricao.setTurma(turma);
            inscricao.setStatus("ATIVA");
            novasInscricoes.add(inscricao);
        }

        //List<Inscricao> salvas = inscricaoRepository.saveAll(novasInscricoes);
        inscricaoRepository.saveAll(novasInscricoes);
        return inscricaoMapper.toInscricoesDTO(novasInscricoes);
    }

    private void validarPeriodoInscricaoAtivo() {
        periodoRepository.findPeriodoAtual(LocalDateTime.now())
                .orElseThrow(() -> new RegraNegocioException("Não há um período de inscrição aberto no momento."));
    }

    // talvez essa função esteja contra os principios solid
    private void validarConfrontoHorarios(Discente discente, List<Turma> novas) {
        // Checa conflito entre as turmas selecionadas agora
        for (int i = 0; i < novas.size(); i++) {
            for (int j = i + 1; j < novas.size(); j++) {
                if (novas.get(i).getHorario().temConflitoCom(novas.get(j).getHorario())) {
                    throw new RegraNegocioException("Conflito de horário entre " +
                            novas.get(i).getDisciplina().getNome() + " e " + novas.get(j).getDisciplina().getNome());
                }
            }
        }

        // Checa conflito com inscrições já confirmadas do aluno
        List<Inscricao> atuais = inscricaoRepository.findByDiscenteId(discente.getId());
        for (Turma n : novas) {
            for (Inscricao a : atuais) {
                if (n.getHorario().temConflitoCom(a.getTurma().getHorario())) {
                    throw new RegraNegocioException("A turma " + n.getDisciplina().getNome() + " conflita com sua grade atual.");
                }
            }
        }
    }

    @Transactional
    public void cancelarInscricao(InscricaoRequestDTO dto) {
        inscricaoRepository.deleteByDiscenteIdAndTurmaIdIn(dto.discenteId(), dto.turmasIds());
    }

    // uso futuro para um possível método de trancamento da disciplina
//    @Transactional
//    public void trancarInscricao(InscricaoRequestDTO dto) {
//        List<Inscricao> inscricoes = inscricaoRepository.findByDiscenteId(dto.discenteId());
//        List<Inscricao> inscricoesParaTrancar = inscricoes.stream()
//                .filter(inscricao -> dto.turmasIds().contains(inscricao.getTurma().getId()))
//                .toList();
//        if (inscricoesParaTrancar.isEmpty()) {
//            throw new IllegalArgumentException("Nenhuma inscrição encontrada para trancar.");
//        }
//        inscricoesParaTrancar.forEach(Inscricao::trancar);
//        inscricaoRepository.saveAll(inscricoesParaTrancar);
//    }
}