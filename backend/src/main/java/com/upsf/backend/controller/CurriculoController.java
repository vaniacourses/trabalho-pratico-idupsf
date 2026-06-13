package com.upsf.backend.controller;

import com.upsf.backend.create.CurriculoCreate;
import com.upsf.backend.dto.CurriculoDTO;
import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.service.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos/{departamentoId}/cursos/{cursoId}/curriculos")
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;

    @GetMapping
    public ResponseEntity<List<CurriculoDTO>> listarCurriculos(
            @PathVariable Long departamentoId,
            @PathVariable Long cursoId) {

        return ResponseEntity.ok(
                curriculoService.listarPorCurso(departamentoId, cursoId));
    }

    @GetMapping("/{curriculoId}")
    public ResponseEntity<CurriculoDTO> buscarPorId(
            @PathVariable Long departamentoId,
            @PathVariable Long cursoId,
            @PathVariable Long curriculoId) {

        return ResponseEntity.ok(
                curriculoService.buscarPorId(cursoId, curriculoId, departamentoId));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> adicionarCurriculo(
            @PathVariable Long departamentoId,
            @PathVariable Long cursoId,
            @RequestBody CurriculoCreate curriculoCreate) {

        CursoDTO dto = curriculoService.criarCurriculo(cursoId, curriculoCreate, departamentoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PatchMapping("/{curriculoId}/atual")
    public ResponseEntity<CursoDTO> definirCurriculoAtual(
            @PathVariable Long departamentoId,
            @PathVariable Long cursoId,
            @PathVariable Long curriculoId) {

        return ResponseEntity.ok(
                curriculoService.definirCurriculoAtual(cursoId, curriculoId, departamentoId));
    }

    @DeleteMapping("/{curriculoId}")
    public ResponseEntity<Void> removerCurriculo(
            @PathVariable Long departamentoId,
            @PathVariable Long cursoId,
            @PathVariable Long curriculoId) {

        curriculoService.removerCurriculo(cursoId, curriculoId, departamentoId);
        return ResponseEntity.noContent().build();
    }
}
