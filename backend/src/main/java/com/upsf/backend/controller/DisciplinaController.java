package com.upsf.backend.controller;

import com.upsf.backend.create.DisciplinaCreate;
import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @GetMapping
    public List<DisciplinaDTO> retornarListaDisciplinasPeloNome(@RequestParam String nome) {
        return disciplinaService.retornarListaDisciplinasPeloNome(nome);
    }

    @GetMapping("{id_disciplina}")
    public DisciplinaDTO retornarDisciplina(@PathVariable Long id_disciplina) {
        return disciplinaService.retornarDisciplina(id_disciplina);
    }

    // trocar o retorno para um DisciplinaResponse no futuro
    @PostMapping
    public DisciplinaDTO cadastrarDisciplina(@RequestBody DisciplinaCreate disciplinaCreate) {
        return disciplinaService.cadastrarDisciplina(disciplinaCreate);
    }

    // trocar o retorno para um DisciplinaResponse
    @PutMapping("{id_disciplina}")
    public DisciplinaDTO alterarDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
        return disciplinaService.alterarDisciplina(disciplinaDTO);
    }

    @DeleteMapping("{id_disciplina}")
    public void deletarDisciplina(@PathVariable Long id_disciplina) {
        disciplinaService.deletarDisciplina(id_disciplina);
    }
}
