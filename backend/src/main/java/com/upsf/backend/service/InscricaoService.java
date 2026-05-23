package com.upsf.backend.service;

import com.upsf.backend.create.InscricaoCreate;
import com.upsf.backend.dto.InscricaoDTO;
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
import com.upsf.backend.inscricao.ValidacaoInscricao;
import com.upsf.backend.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private TurmaRepository turmaRepository;
    @Autowired
    private InscricaoMapper inscricaoMapper;
    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private List<ValidacaoInscricao> validacoesInscricao;

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

    public List<InscricaoDTO> listarInscricoesFeitasPorId(Long discenteId) {
        List<Inscricao> inscricoesFeitas = inscricaoRepository.findByDiscenteId(discenteId);
        return inscricaoMapper.toInscricoesDTO(inscricoesFeitas);
    }

    @Transactional
    public List<InscricaoDTO> realizarInscricao(InscricaoCreate dto) {
        validarPeriodoInscricaoAtivo();

        Discente discente = discenteRepository.findById(dto.discenteId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Discente não encontrado"));

        List<Turma> turmasSelecionadas = turmaRepository.findAllById(dto.turmasIds());

        validacoesInscricao.forEach(validacao -> validacao.validar(discente, turmasSelecionadas));

        List<Inscricao> novasInscricoes = turmasSelecionadas.stream()
                .map(turma -> new Inscricao(turma, discente, "ATIVA"))
                .toList();

        inscricaoRepository.saveAll(novasInscricoes);
        return inscricaoMapper.toInscricoesDTO(novasInscricoes);
    }

    private void validarPeriodoInscricaoAtivo() {
        periodoRepository.findPeriodoInscricaoAtual(LocalDateTime.now())
                .orElseThrow(() -> new RegraNegocioException("Não há um período de inscrição aberto no momento."));
    }

    @Transactional
    public void cancelarInscricao(Long discenteId, List<Long> turmasIds) {
        inscricaoRepository.deleteByDiscenteIdAndTurmaIdIn(discenteId, turmasIds);
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
    public List<Inscricao> listarInscricoesPorTurma(Long id) {
        return inscricaoRepository.findByTurmaId(id);
    }

    public void deletarInscricoesPorTurma(Long turmaId) {
        inscricaoRepository.deleteByTurmaId(turmaId);
    }
}