package com.upsf.backend.controller;

import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.service.DepartamentoService;
import com.upsf.backend.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departamentos")

public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> listarTodos() {
        return ResponseEntity.ok(departamentoService.listarTodos());
    }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<CursoDTO>> listarTodos(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.listarCursosPorDepartamento(id));
    }

    @GetMapping("/{idDepartamento}/disciplinas")
    public List<DisciplinaDTO> listarDisciplinasPorDepartamento(@PathVariable Long idDepartamento) {
        return disciplinaService.retornarListaDisciplinasPeloDepartamento(idDepartamento);
    }
}
