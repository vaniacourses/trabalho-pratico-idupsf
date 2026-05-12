package com.upsf.backend.controller;


import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/quadro") // caminhos aqui são placeholders (Quadro de Horários)
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    // Requisição Get para Turmas Ativas (Para Quadro de Horários)
    @GetMapping
    public List<TurmaDTO> buscarTurmasAtivas() {
        return turmaService.buscarTurmasAtivas();
    }

    // Dependência de @Valid ainda inexistente
    // Ainda a implementar - Cláudio
    @PostMapping
    public void cadastrarTurma(@RequestBody TurmaCreate turmaCreate) { // Passa um Objeto Transiente
        // return turmaService.cadastrarTurma(turmaCreate);
    }
}
