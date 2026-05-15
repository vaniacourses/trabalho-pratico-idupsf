package com.upsf.backend.service;

import com.upsf.backend.dto.DisciplinaCursadaDTO;
import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.dto.HistoricoDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.DisciplinaMapper;
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
    @Autowired
    private DisciplinaMapper disciplinaMapper;

    // talvez seja melhor buscar por matricula
    public Historico buscarPorDiscente(Long discenteId) {
        return historicoRepository.findByDiscenteId(discenteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Histórico não encontrado para o aluno ID: " + discenteId));
    }

    public Float buscarCR(Long idAluno) {
        Historico historico = historicoRepository.findByDiscenteId(idAluno)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Histórico não encontrado para o discente: " + idAluno));

        return historico.getCoeficienteRend();
    }

    public List<DisciplinaCursadaDTO> buscarDisciplinasCursadasDTO(Long idAluno) {
        Historico historico = historicoRepository.findByDiscenteId(idAluno)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Histórico não encontrado para o discente: " + idAluno));

        // Percorre a lista de disciplinas cursadas e converte uma por uma para o DTO
        return historico.getListaDisciplinas().stream()
                .map(cursada -> new DisciplinaCursadaDTO(
                        cursada.getId(),
                        cursada.getTurma().getDisciplina().getNome(), // Navegando até a Disciplina para pegar o nome
                        cursada.getTurma().getDisciplina().getCod(),  // Navegando até a Disciplina para pegar o código
                        cursada.getNota(),
                        cursada.getNotaVS(),
                        cursada.getStatusFinal().name(), // Convertendo o Enum (APROVADO, REPROVADO) para String
                        cursada.getCargaHoraria(),
                        cursada.getPeriodo() // Ou cursada.getTurma().getAnoSemestre() dependendo da sua preferência
                ))
                .toList();
    }

    // Função usada em "listarTurmasDisponiveis" de InscricaoService
    // Deve retornar a versão Entity de Disciplina
    public List<Disciplina> buscarDisciplinasEntitiesAprovadas(Long discenteId) {
        return historicoRepository.findDisciplinasByDiscenteAndStatus(
                discenteId,
                DisciplinaCursada.StatusFinal.APROVADO
        );
    }

    // Versao da funcao acima que retorna um DTO
    public List<DisciplinaDTO> buscarDisciplinasEntitiesAprovadasDTO(Long discenteId) {
        List<Disciplina> disciplinas = historicoRepository.findDisciplinasByDiscenteAndStatus(
                discenteId,
                DisciplinaCursada.StatusFinal.APROVADO
        );
        return disciplinaMapper.toDisciplinasDTO(disciplinas);
    }


    public void atualizarCoeficiente(Long discenteId) {
        Historico historico = buscarPorDiscente(discenteId);
        historico.calcularCR();
        historicoRepository.save(historico);
    }
}
