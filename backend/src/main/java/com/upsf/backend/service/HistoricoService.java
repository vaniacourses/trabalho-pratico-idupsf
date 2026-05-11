package com.upsf.backend.service;

import com.upsf.backend.dto.HistoricoDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.HistoricoMapper;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.DisciplinaCursada;
import com.upsf.backend.model.Historico;
import com.upsf.backend.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoService {
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private HistoricoMapper historicoMapper;

    // talvez seja melhor buscar por matricula
    public Historico buscarPorDiscente(Long discenteId) {
        return historicoRepository.findByDiscenteId(discenteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Histórico não encontrado para o aluno ID: " + discenteId));
    }

    public List<Disciplina> buscarDisciplinasConcluidas(Long discenteId) {
        Historico historico = buscarPorDiscente(discenteId);
        List<Disciplina> disciplinasConcluidas = new ArrayList<>();
        for (DisciplinaCursada dc : historico.getListaDisciplinas()) {
            if ("APROVADO".equalsIgnoreCase(dc.getStatusFinal())) {
                disciplinasConcluidas.add(dc.getTurma().getDisciplina());
            }
        }
        return disciplinasConcluidas;
    }

    public void atualizarCoeficiente(Long discenteId) {
        Historico historico = buscarPorDiscente(discenteId);
        historico.calcularCR();
        historicoRepository.save(historico);
    }
}
