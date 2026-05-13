package com.upsf.backend.controller;


import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/quadro") // caminhos aqui são placeholders (Quadro de Horários)
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    // Requisição GET para Turmas Ativas (Para Quadro de Horários)
    @GetMapping
    public List<TurmaDTO> buscarTurmasAtivas() {
        return turmaService.buscarTurmasAtivas();
    }

    // Dependência de @Valid ainda inexistente
    // Requisição POST para Criação/Cadastro de Novas Turmas (Tarefa do Coordenador)
    @PostMapping
    public TurmaDTO cadastrarTurma(@RequestBody TurmaCreate turmaCreate) { // Passa um Objeto Transiente
        return turmaService.cadastrarTurma(turmaCreate);
    }

    // Requisição PUT para Atualização dos Dados de uma Turma
    @PutMapping
    public TurmaDTO alterarTurma(@RequestBody TurmaDTO turma) { // Passa um Objeto Destacado
        return turmaService.alterarTurma(turma);
    }

    // Requisição DELETE para Exclução de uma Turma
    @DeleteMapping("{idTurma}")
    public void removerTurmaPorId(@PathVariable("idTurma") long id) {
        turmaService.removerTurmaPorId(id);
        // Espera receber um Objeto Persistente
    }
}
