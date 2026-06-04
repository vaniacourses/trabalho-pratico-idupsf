package com.upsf.backend.service;

import com.upsf.backend.create.PeriodoCreate;
import com.upsf.backend.dto.PeriodoDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.PeriodoMapper;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Periodo;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.PeriodoRepository;
import com.upsf.backend.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodoService {

    private final PeriodoRepository periodoRepository;
    private final TurmaRepository turmaRepository;
    private final PeriodoMapper periodoMapper; // Injetado via Lombok
    private final TurmaMapper turmaMapper;

    @Transactional
    public PeriodoDTO criarPeriodo(PeriodoCreate dto) {
        if (periodoRepository.existsBySemestre(dto.semestre())) {
            throw new RegraNegocioException("O semestre " + dto.semestre() + " já está cadastrado.");
        }
        validarDatas(dto);

        Periodo periodo = periodoMapper.toPeriodo(dto);

        periodoRepository.save(periodo);
        return periodoMapper.toPeriodoDTO(periodo); // Uso do Mapper
    }

    @Transactional(readOnly = true)
    public List<PeriodoDTO> listarTodos() {
        List<Periodo> periodos = periodoRepository.findAll();
        return periodoMapper.toPeriodosDTO(periodos);
    }

    @Transactional
    public PeriodoDTO atualizarDatas(Long id, PeriodoCreate dto) {
        Periodo periodo = buscarPeriodoPorId(id);
        validarDatas(dto);

        periodo.setDataInicio(dto.dataInicio());
        periodo.setDataFim(dto.dataFim());
        periodo.setDataInicioInscricao(dto.dataInicioInscricao());
        periodo.setDataFimInscricao(dto.dataFimInscricao());

        periodoRepository.save(periodo);
        return periodoMapper.toPeriodoDTO(periodo); // Uso do Mapper
    }

    @Transactional(readOnly = true)
    public List<TurmaDTO> listarTurmasDoPeriodo(Long periodoId) {
        Periodo periodo = buscarPeriodoPorId(periodoId);
        return turmaMapper.toTurmasDTO(periodo.getTurmas());
    }

    @Transactional
    public void adicionarTurma(Long periodoId, Long turmaId) {
        Periodo periodo = buscarPeriodoPorId(periodoId);
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada."));
        periodo.adicionarTurmas(turma);
        periodoRepository.save(periodo);
    }

    @Transactional
    public void removerTurma(Long periodoId, Long turmaId) {
        Periodo periodo = buscarPeriodoPorId(periodoId);
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada."));
        periodo.removerTurmas(turma);
        periodoRepository.save(periodo);
    }

    // Métodos auxiliares internos, talvez mudem de lugar

    private Periodo buscarPeriodoPorId(Long id) {
        return periodoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Período não encontrado."));
    }

    private void validarDatas(PeriodoCreate dto) {
        if (dto.dataFim().isBefore(dto.dataInicio())) {
            throw new RegraNegocioException("A data de fim do período não pode ser anterior à data de início.");
        }
        if (dto.dataFimInscricao().isBefore(dto.dataInicioInscricao())) {
            throw new RegraNegocioException("A data final de inscrição não pode ser anterior à data de início de inscrição.");
        }
    }
}