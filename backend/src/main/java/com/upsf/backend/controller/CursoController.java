package com.upsf.backend.controller;

import com.upsf.backend.create.CursoCreate;
import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.dto.DocenteDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.CurriculoService;
import com.upsf.backend.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departamentos/{departamentoId}/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarTodos(@PathVariable Long departamentoId) {
        return ResponseEntity.ok(cursoService.listarTodosPorDepartamento(departamentoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarPorId(
            @PathVariable Long departamentoId,
            @PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarDTOPorId(id, departamentoId));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> criar(
            @PathVariable Long departamentoId,
            @RequestBody CursoCreate cursoCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criar(cursoCreate, departamentoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> atualizar(
            @PathVariable Long departamentoId,
            @PathVariable Long id,
            @RequestBody CursoCreate cursoCreate) {
        return ResponseEntity.ok(cursoService.atualizar(id, cursoCreate, departamentoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long departamentoId,
            @PathVariable Long id) {
        cursoService.deletar(id, departamentoId);
        return ResponseEntity.noContent().build();
    }
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