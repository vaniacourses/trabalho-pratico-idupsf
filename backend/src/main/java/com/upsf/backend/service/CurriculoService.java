package com.upsf.backend.service;

import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.CursoMapper;
import com.upsf.backend.model.Curriculo;
import com.upsf.backend.model.Curso;
import com.upsf.backend.repository.CurriculoRepository;
import com.upsf.backend.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public CursoDTO criarCurriculo(Long cursoId, String cod) {
        if (curriculoRepository.existsByCod(cod)) {
            throw new EntidadeJaExistenteException(
                "Já existe um currículo com o código: " + cod
            );
        }
        Curso curso = cursoService.buscarCursoPorId(cursoId);

        Curriculo curriculo = curriculoRepository.save(new Curriculo(cod));
        curso.adicionarCurriculo(curriculo);

        cursoRepository.save(curso);

        return cursoMapper.toCursoDTO(curso);
    }

    @Transactional
    public CursoDTO removerCurriculo(Long cursoId, Long curriculoId) {
        Curso curso = cursoService.buscarCursoPorId(cursoId);
        Curriculo curriculo = curriculoRepository
                .findByIdAndCursoId(curriculoId, cursoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Currículo de id = " + curriculoId + " não encontrado no curso de id = " + cursoId + "."
                ));

        if (curriculo.getCod().equals(curso.getCodCurriculoAtual())) {
            throw new RegraNegocioException(
                "Não é possível remover o currículo ativo do curso. Defina outro currículo como atual antes de remover."
            );
        }

        curso.removerCurriculo(curriculo);
        cursoRepository.save(curso);
        return cursoMapper.toCursoDTO(curso);
    }


    @Transactional
    public CursoDTO definirCurriculoAtual(Long cursoId, Long curriculoId) {
        Curso curso = cursoService.buscarCursoPorId(cursoId);
        Curriculo curriculo = curriculoRepository
                .findByIdAndCursoId(curriculoId, cursoId)
                .orElseThrow(() -> new RegraNegocioException(
                        "Currículo de id = " + curriculoId + " não pertence ao curso de id = " + cursoId + "."
                ));

        curso.setCodCurriculoAtual(curriculo.getCod());
        cursoRepository.save(curso);
        return cursoMapper.toCursoDTO(curso);
    }

    public Curriculo buscarCurriculoPorId(Long id) {
        return curriculoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Currículo com id = " + id + " não encontrado."
                ));
    }
}