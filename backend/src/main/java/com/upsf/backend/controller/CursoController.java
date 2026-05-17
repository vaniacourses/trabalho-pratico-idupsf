package com.upsf.backend.controller;

import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.CursoMapper;
import com.upsf.backend.model.Curso;
import com.upsf.backend.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @GetMapping
    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toCursoDTO)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDTO criar(@RequestBody CursoDTO dto) {

        Curso curso = cursoMapper.toCurso(dto);

        Curso salvo = cursoRepository.save(curso);

        return cursoMapper.toCursoDTO(salvo);
    }

    @PutMapping("/{id}")
    public CursoDTO atualizar(@PathVariable Long id,
                              @RequestBody CursoDTO dto) {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException("Curso não encontrado"));

        curso.setCod(dto.cod());
        curso.setNome(dto.nome());
        curso.setDuracaoMin(dto.duracaoMin());
        curso.setDuracaoMax(dto.duracaoMax());
        curso.setCodCurriculoAtual(dto.codCurriculoAtual());
        curso.setTurno(dto.turno());

        Curso atualizado = cursoRepository.save(curso);

        return cursoMapper.toCursoDTO(atualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException("Curso não encontrado"));

        cursoRepository.delete(curso);
    }
}