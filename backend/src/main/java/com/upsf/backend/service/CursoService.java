package com.upsf.backend.service;

import com.upsf.backend.create.CursoCreate;
import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.CursoMapper;
import com.upsf.backend.model.Curriculo;
import com.upsf.backend.model.Curso;
import com.upsf.backend.repository.CurriculoRepository;
import com.upsf.backend.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {


    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Transactional(readOnly = true)
    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toCursoDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CursoDTO buscarDTOPorId(Long id) {
        return cursoMapper.toCursoDTO(buscarCursoPorId(id));
    }

    @Transactional
    public CursoDTO criar(CursoCreate cursoCreate) {
        if (cursoRepository.existsByCod(cursoCreate.cod())) {
            throw new EntidadeJaExistenteException(
                "Já existe um curso com o código: " + cursoCreate.cod()
            );
        }
        validarDuracoes(cursoCreate.duracaoMin(), cursoCreate.duracaoMax());

        /*
        if (cursoCreate.codCurriculoAtual() != null && !cursoCreate.codCurriculoAtual().isBlank()) {
            if (!curriculoRepository.existsByCod(cursoCreate.codCurriculoAtual())) {
                throw new EntidadeNaoEncontradaException(
                    "Currículo com código '" + cursoCreate.codCurriculoAtual() + "' não encontrado."
                );
            }
        }
        */

        Curso curso = cursoMapper.toCurso(cursoCreate);
        return cursoMapper.toCursoDTO(cursoRepository.save(curso));
    }

    @Transactional
    public CursoDTO atualizar(Long id, CursoCreate cursoCreate) {
        Curso curso = buscarCursoPorId(id);

        validarDuracoes(cursoCreate.duracaoMin(), cursoCreate.duracaoMax());

        if (!curso.getCod().equals(cursoCreate.cod()) && cursoRepository.existsByCod(cursoCreate.cod())) {
            throw new EntidadeJaExistenteException(
                "Já existe um curso com o código: " + cursoCreate.cod()
            );
        }

        curso.setCod(cursoCreate.cod());
        curso.setNome(cursoCreate.nome());
        curso.setDuracaoMin(cursoCreate.duracaoMin());
        curso.setDuracaoMax(cursoCreate.duracaoMax());
        curso.setTurno(cursoCreate.turno());

        return cursoMapper.toCursoDTO(cursoRepository.save(curso));
    }

    @Transactional
    public void deletar(Long id) {
        cursoRepository.delete(buscarCursoPorId(id));
    }

    public List<CursoDTO> listarOptions() {
        return cursoRepository.findAllAsOptions();
    }

    // Método auxiliar público — reutilizado por CurriculoService, DiscenteService, CoordenadorService
    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Curso com id = " + id + " não encontrado."
                ));
    }

    private void validarDuracoes(int duracaoMin, int duracaoMax) {
        if (duracaoMin <= 0 || duracaoMax <= 0) {
            throw new RegraNegocioException(
                "As durações mínima e máxima devem ser maiores que zero."
            );
        }
        if (duracaoMin > duracaoMax) {
            throw new RegraNegocioException(
                "A duração mínima não pode ser maior que a duração máxima."
            );
        }
    }
}