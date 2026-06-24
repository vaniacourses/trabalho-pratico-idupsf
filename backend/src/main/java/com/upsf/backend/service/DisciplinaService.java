package com.upsf.backend.service;


import com.upsf.backend.create.DisciplinaCreate;
import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.DisciplinaMapper;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    public List<DisciplinaDTO> retornarListaDisciplinasPeloNome(@RequestParam String nome) {
        List<Disciplina> disciplinas = disciplinaRepository.findByNomeContainingIgnoreCase(nome);
        return disciplinaMapper.toDisciplinasDTO(disciplinas);
    }

    public List<DisciplinaDTO> retornarTodasAsDisciplinas() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        return disciplinaMapper.toDisciplinasDTO(disciplinas);
    }

    public DisciplinaDTO retornarDisciplina(@PathVariable Long id_disciplina) {
        Disciplina disciplina = disciplinaRepository.findById(id_disciplina)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina de id = " + id_disciplina + " não encontrada."
                        ));
        return disciplinaMapper.toDisciplinaDTO(disciplina);
    }

    // trocar o retorno?
    public DisciplinaDTO cadastrarDisciplina(DisciplinaCreate disciplinaCreate) {
        Disciplina disciplina = disciplinaMapper.toDisciplina(disciplinaCreate);

        disciplina.setStatus(Disciplina.Status.ATIVA);

        if (disciplinaCreate.preRequisitosIds() != null && !disciplinaCreate.preRequisitosIds().isEmpty()) {
            // busca as entidades com os ids
            List<Disciplina> preReqs = disciplinaRepository.findAllById(disciplinaCreate.preRequisitosIds());
            disciplina.setPreRequisitos(preReqs);
        }

        return disciplinaMapper.toDisciplinaDTO(disciplinaRepository.save(disciplina));
    }

    // trocar o retorno
    public DisciplinaDTO alterarDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = disciplinaMapper.toDisciplina(disciplinaDTO);
        return disciplinaMapper.toDisciplinaDTO(disciplinaRepository.save(disciplina));
    }

    public void deletarDisciplina(Long id_disciplina) {
        Disciplina disciplina = disciplinaRepository.findById(id_disciplina)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Disciplina não encontrada."));

        disciplina.setStatus(Disciplina.Status.INATIVA);

        disciplinaRepository.save(disciplina);
    }

    public List<DisciplinaDTO> retornarListaDisciplinasPeloDepartamento(Long departamentoId) {
        List<Disciplina> disciplinas = disciplinaRepository.findByDepartamentoId(departamentoId);
        return disciplinaMapper.toDisciplinasDTO(disciplinas);
    }
}
