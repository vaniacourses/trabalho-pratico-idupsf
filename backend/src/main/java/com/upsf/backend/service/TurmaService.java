package com.upsf.backend.service;

import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Horario;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.*;
import com.upsf.backend.spec.TurmaSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private TurmaMapper turmaMapper;

    // Função de Busca de Todas as Turmas (TurmaService)
    public List<TurmaDTO> buscarTurmasAtivas() {

        List<Turma> turmas = turmaRepository.buscarTurmasAtivas();
        return turmaMapper.toTurmasDTO(turmas);
    }

    // Função de Busca de Turmas com Filtros para TurmaController (Para Quadro de Horários)
    public List<TurmaDTO> listarQuadroTurmas(String nomeCodDisciplina, String departamento, String docente, String anoSemestre) {
        Specification<Turma> spec = TurmaSpec.comFiltros(nomeCodDisciplina, departamento, docente, anoSemestre);
        List<Turma> turmas = turmaRepository.findAll(spec);
        return turmaMapper.toTurmasDTO(turmas);
    }

    public TurmaDTO buscarTurmaPorId(@PathVariable Long idTurma) {
        Turma turma = turmaRepository.buscarTurmaPorId(idTurma)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Turma de id = " + idTurma + " não encontrada."
                ));

        // Retorno convertendo para TurmaDTO
        return turmaMapper.toTurmaDTO(turma);
    }

    // Função de Criação/Cadastro de Turmas (Tarefa do Coordenador)
    public TurmaDTO cadastrarTurma(TurmaCreate turmaCreate) {
        Turma turma = turmaMapper.toTurma(turmaCreate);
        turma = turmaRepository.save(turma);

        if (turmaCreate.docenteId() != null)  {
            turma.setStatus(Turma.StatusTurma.ATIVA);
             Docente docente = docenteRepository.findById(turmaCreate.docenteId())
                  .orElseThrow(() -> new EntidadeNaoEncontradaException(
                                        "Docente de id = " + turmaCreate.horarioId() + " não encontrado."));
             turma.setDocente(docente);
        } else {
            turma.setStatus(Turma.StatusTurma.INATIVA);
        }

        if (turmaCreate.horarioId() != null) {

            Horario horario = horarioRepository.findById(turmaCreate.horarioId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Horário de id = " + turmaCreate.horarioId() + " não encontrado."
                    ));
            turma.setHorario(horario);
        }

        if (turmaCreate.disciplinaId() != null) {
            Disciplina disciplina = disciplinaRepository.findById(turmaCreate.disciplinaId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Disciplina de id = " + turmaCreate.disciplinaId() + " não encontrada."
                    ));
            turma.setDisciplina(disciplina);
        }


        // Retorno convertendo para TurmaDto
        return turmaMapper.toTurmaDTO(turmaRepository.save(turma));
    }

    // Função de Alteração de Dados de Turmas (Tarefa do Coordenador)
    public TurmaDTO alterarTurma(@RequestBody TurmaDTO turmaDto) {
        Turma turma = turmaMapper.toTurma(turmaDto);
        return turmaMapper.toTurmaDTO(turmaRepository.save(turma));
    }


    public void removerTurmaPorId(long id) {
        // Ordem importa: Deleção das Inscrições Primeiro para respeitar Foreign Key
        // Manter consistência de dados
        inscricaoRepository.deleteByTurmaId(id);
        turmaRepository.deleteById(id);
    }
}