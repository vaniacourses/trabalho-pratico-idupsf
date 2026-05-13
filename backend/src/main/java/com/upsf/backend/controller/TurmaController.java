package com.upsf.backend.controller;


import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TurmaDTO>> buscarTurmasAtivas() {
        List<TurmaDTO> turmasAtivas = turmaService.buscarTurmasAtivas();
        return ResponseEntity.ok(turmasAtivas); // Status 200
    }

    // Dependência de @Valid ainda inexistente
    // Requisição POST para Criação/Cadastro de Novas Turmas (Tarefa do Coordenador)
    @PostMapping
    public ResponseEntity<TurmaDTO> cadastrarTurma(@RequestBody TurmaCreate turmaCreate) { // Passa um Objeto Transiente
        TurmaDTO novaTurma = turmaService.cadastrarTurma(turmaCreate);
        return new ResponseEntity<>(novaTurma, HttpStatus.CREATED); // Status 201
    }

    // Requisição PUT para Atualização dos Dados de uma Turma
    @PutMapping
    public ResponseEntity<TurmaDTO> alterarTurma(@RequestBody TurmaDTO turmaDto) { // Passa um Objeto Destacado
        return ResponseEntity.ok(turmaService.alterarTurma(turmaDto));
    }

    // Requisição DELETE para Exclução de uma Turma
    @DeleteMapping("{idTurma}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Status 204 (Sucesso, mas sem corpo)
    public void removerTurmaPorId(@PathVariable("idTurma") long id) {
        turmaService.removerTurmaPorId(id);
        // Espera receber um Objeto Persistente
    }
}
