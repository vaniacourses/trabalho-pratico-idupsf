package com.upsf.backend.service;

import com.upsf.backend.create.InscricaoCreate;
import com.upsf.backend.dto.InscricaoDTO;
import com.upsf.backend.dto.InscricaoResponseDTO;
import com.upsf.backend.dto.InscricaoUpdateDTO;
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
import java.util.stream.Collectors;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoMapper inscricaoMapper;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private HistoricoService historicoService;
    
    @Autowired
    private TurmaMapper turmaMapper;
    
    @Autowired
    private TurmaRepository turmaRepository;

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
        List<Turma> turmasAtivas = turmaRepository.buscarTurmasAtivasComRequisitos();
        Set<Disciplina> inscritas = new HashSet<>(inscricaoRepository.findByDiscenteId(discenteId))
                .stream()
                .map(inscricao -> inscricao.getTurma().getDisciplina())
                .collect(Collectors.toSet());

        List<Turma> disponiveis = turmasAtivas.stream()
                .filter(turma -> !inscritas.contains(turma.getDisciplina()))
                .filter(turma -> !concluidas.contains(turma.getDisciplina()))
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

    @Transactional(readOnly = true)
    public List<InscricaoResponseDTO> listarInscricoesPorTurma(Long turmaId) {
        if (!turmaRepository.existsById(turmaId)) {
            throw new EntidadeNaoEncontradaException("Turma não encontrada.");
        }

        List<Inscricao> inscricoes = inscricaoRepository.findByTurmaId(turmaId);

        return inscricoes.stream()
                .map(inscricao -> new InscricaoResponseDTO(

                        inscricao.getId(),
                        inscricao.getDiscente().getNome(),
                        inscricao.getDiscente().getMatricula(),
                        inscricao.getNota(),
                        inscricao.getNotaVS(),
                        inscricao.getFrequencia(),
                        inscricao.getStatus()
                ))
                .toList();
    }

    @Transactional
    public void atribuirNotasEFrequencias(Long turmaId, Long docenteId, List<InscricaoUpdateDTO> atualizacoes) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada."));

        // Validação Analítica: O docente tentando alterar as notas é o dono da turma?
        if (turma.getDocente() == null || !turma.getDocente().getId().equals(docenteId)) {
            throw new RegraNegocioException("Operação negada: O docente informado não é o responsável por esta turma.");
        }

        // Validação de Status: Turmas FECHADAS não deveriam receber alteração de notas de forma direta
        if (turma.getStatus() == Turma.StatusTurma.FECHADA) {
            throw new RegraNegocioException("Não é possível alterar notas de uma turma já encerrada.");
        }

        for (InscricaoUpdateDTO dto : atualizacoes) {
            Inscricao inscricao = inscricaoRepository.findById(dto.inscricaoId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Inscrição com ID " + dto.inscricaoId() + " não encontrada."));

            // Validação de integridade: A inscrição pertence à turma informada?
            if (!inscricao.getTurma().getId().equals(turmaId)) {
                throw new RegraNegocioException("A inscrição " + dto.inscricaoId() + " não pertence à turma " + turmaId);
            }

            // Atualiza os dados
            if (dto.nota() != null) inscricao.setNota(dto.nota());
            if (dto.notaVS() != null) inscricao.setNotaVS(dto.notaVS());
            if (dto.frequencia() != null) inscricao.setFrequencia(dto.frequencia());

            inscricaoRepository.save(inscricao);
        }
    }

    public void deletarInscricoesPorTurma(Long turmaId) {
        inscricaoRepository.deleteByTurmaId(turmaId);
    }
}