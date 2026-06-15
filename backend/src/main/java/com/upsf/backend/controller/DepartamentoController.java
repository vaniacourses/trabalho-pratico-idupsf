package com.upsf.backend.controller;

import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.service.DepartamentoService;
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

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> listarTodos() {
        return ResponseEntity.ok(departamentoService.listarTodos());
    }
}
