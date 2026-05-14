package com.upsf.backend.controller;

import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/opcoes")
public class OpcaoController {

    @Autowired
    private DepartamentoService departamentoService;

    // private CursoService cursoService;
    // private CurriculoService curriculoService;

//    @GetMapping("/departamentos")
//    public ResponseEntity<List<DepartamentoDTO>> departamentos() {
//        return ResponseEntity.ok(departamentoService.listarOptions());
//    }

    // Necessário discutir certas relações entre Turma e (Curso, Currículo) para fazer esses filtros de busca
    // Placeholders temporários
//    @GetMapping("/cursos")
//    public ResponseEntity<List<CursoOptionDTO>> cursos() {
//        return ResponseEntity.ok(cursoService.listarOptions());
//    }

//    @GetMapping("/curriculos")
//    public ResponseEntity<List<CurriculoOptionDTO>> curriculos(@RequestParam Long cursoId) {
//        return ResponseEntity.ok(curriculoService.listarOptionsPorCurso(cursoId));
//    }
}
