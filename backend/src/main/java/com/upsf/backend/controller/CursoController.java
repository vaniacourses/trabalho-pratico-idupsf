package com.upsf.backend.controller;

import com.upsf.backend.create.CursoCreate;
import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.dto.DocenteDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.CurriculoService;
import com.upsf.backend.service.CursoService;
import com.upsf.backend.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarDTOPorId(id));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> criar(@RequestBody CursoCreate cursoCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criar(cursoCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CursoCreate cursoCreate) {
        return ResponseEntity.ok(cursoService.atualizar(id, cursoCreate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/departamento/docentes")
    public ResponseEntity<List<DocenteDTO>> listarDocentesDoDepartamentoDoCurso(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.listarDocentesDoDepartamentoDoCurso(id));
    }

    @GetMapping("/{id}/curriculos/turmas")
    public ResponseEntity<List<TurmaDTO>> listarTurmasDosCurriculosDoCurso(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.listarTurmasDosCurriculosDoCurso(id));
    }

}