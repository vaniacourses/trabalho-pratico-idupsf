package com.upsf.backend.service;

import com.upsf.backend.create.CursoCreate;
import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.CursoMapper;
import com.upsf.backend.model.Curso;
import com.upsf.backend.model.Departamento;
import com.upsf.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {


    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CursoMapper cursoMapper;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private CoordenadorRepository coordenadorRepository;

    // Valida que o curso pertence ao departamento informado na URL.
    // Visibilidade package-private mantida para uso interno (ex.: CurriculoService).
    void buscarCursoDoDepartamento(Long cursoId, Long departamentoId) {
        cursoRepository
                .findByIdAndDepartamentoId(cursoId, departamentoId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Curso com id = " + cursoId +
                                " não pertence ao departamento de id = " + departamentoId + "."
                        ));
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> listarTodosPorDepartamento(Long departamentoId) {
        return cursoRepository.findAllByDepartamentoId(departamentoId)
                .stream()
                .map(cursoMapper::toCursoDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CursoDTO buscarDTOPorId(Long id, Long departamentoId) {
        buscarCursoDoDepartamento(id, departamentoId);
        return cursoMapper.toCursoDTO(buscarCursoPorId(id));
    }

    @Transactional
    public CursoDTO criar(CursoCreate cursoCreate, Long departamentoId) {
        if (cursoRepository.existsByCod(cursoCreate.cod())) {
            throw new EntidadeJaExistenteException(
                    "Já existe um curso com o código: " + cursoCreate.cod()
            );
        }

        // Garante que o departamento do body bate com o da URL
        if (!cursoCreate.idDepartamento().equals(departamentoId)) {
            throw new RegraNegocioException(
                    "O departamento informado no body não corresponde ao departamento da URL."
            );
        }

        validarDuracoes(cursoCreate.duracaoMin(), cursoCreate.duracaoMax());

        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Departamento não encontrado."));

        Curso curso = cursoMapper.toCurso(cursoCreate);
        curso.setDepartamento(departamento);
        cursoRepository.save(curso);
        departamento.adicionarCurso(curso);
        departamentoRepository.save(departamento);

        return cursoMapper.toCursoDTO(curso);
    }

    @Transactional
    public CursoDTO atualizar(Long id, CursoCreate cursoCreate, Long departamentoId) {
        buscarCursoDoDepartamento(id, departamentoId);

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
    public void deletar(Long id, Long departamentoId) {
        buscarCursoDoDepartamento(id, departamentoId);

        if (discenteRepository.existsByCursoId(id)) {
            throw new RegraNegocioException(
                    "Não é possível excluir o curso pois existem discentes vinculados a ele."
            );
        }
        if (coordenadorRepository.existsByCursoId(id)) {
            throw new RegraNegocioException(
                    "Não é possível excluir o curso pois existe um coordenador vinculado a ele."
            );
        }

        cursoRepository.deleteById(id);
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
