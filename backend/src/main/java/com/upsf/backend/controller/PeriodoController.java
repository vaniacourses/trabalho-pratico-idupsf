package com.upsf.backend.controller;

import com.upsf.backend.create.PeriodoCreate;
import com.upsf.backend.dto.PeriodoDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodos")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;


    @PostMapping
    public ResponseEntity<PeriodoDTO> criarPeriodo(@RequestBody PeriodoCreate dto) {
        PeriodoDTO criado = periodoService.criarPeriodo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<PeriodoDTO>> listarTodos() {
        return ResponseEntity.ok(periodoService.listarTodos());
    }

    @GetMapping("/{id}/turmas")
    public ResponseEntity<List<TurmaDTO>> listarTurmasDoPeriodo(@PathVariable Long id) {
        return ResponseEntity.ok(periodoService.listarTurmasDoPeriodo(id));
    }

    @PutMapping("/{id}/datas")
    public ResponseEntity<PeriodoDTO> atualizarDatas(@PathVariable Long id, @RequestBody PeriodoCreate dto) {
        PeriodoDTO atualizado = periodoService.atualizarDatas(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/{periodoId}/turmas/{turmaId}")
    public ResponseEntity<Void> adicionarTurma(@PathVariable Long periodoId, @PathVariable Long turmaId) {
        periodoService.adicionarTurma(periodoId, turmaId);
        return ResponseEntity.noContent().build();
    }

    // Não existe remoção de período, apenas remoção de uma turma da associação com periodo
    @DeleteMapping("/{periodoId}/turmas/{turmaId}")
    public ResponseEntity<Void> removerTurma(@PathVariable Long periodoId, @PathVariable Long turmaId) {
        periodoService.removerTurma(periodoId, turmaId);
        return ResponseEntity.noContent().build();
    }
}
