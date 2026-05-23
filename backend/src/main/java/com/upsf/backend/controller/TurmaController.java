package com.upsf.backend.controller;


import com.upsf.backend.create.TurmaCreate;
import com.upsf.backend.dto.DisciplinaDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/turmas") // caminhos aqui são placeholders (Quadro de Horários)
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    // Requisição GET para Turmas Ativas (Para Quadro de Horários) - Talvez Desnecessário
    @GetMapping
    public ResponseEntity<List<TurmaDTO>> buscarTurmasAtivas() {
        List<TurmaDTO> turmasAtivas = turmaService.buscarTurmasAtivas();
        return ResponseEntity.ok(turmasAtivas); // Status 200
    }

    @GetMapping("{idTurma}")
    public ResponseEntity<TurmaDTO> buscarTurmaPorId(@PathVariable Long idTurma) {
        TurmaDTO turma = turmaService.buscarTurmaPorId(idTurma);
        return ResponseEntity.ok(turma); // Status 200
    }

    // Busca de Turmas com Filtros (Para Quadro de Horários)
    @GetMapping("quadro")
    public ResponseEntity<List<TurmaDTO>> listarQuadroTurmas(
            @RequestParam(required = false) String nomeCodDisciplina,
            @RequestParam(required = false) String departamento,
            @RequestParam(required = false) String docente,
            @RequestParam(required = false) String anoSemestre
    ) {
        return ResponseEntity.ok(turmaService.listarQuadroTurmas(nomeCodDisciplina, departamento, docente, anoSemestre));
    }

    // Dependência de @Valid ainda inexistente
    // Requisição POST para Criação/Cadastro de Novas Turmas (Tarefa do Coordenador)
    @PostMapping
    public ResponseEntity<TurmaDTO> cadastrarTurma(@RequestBody TurmaCreate turmaCreate) { // Passa um Objeto Transiente
        TurmaDTO novaTurma = turmaService.cadastrarTurma(turmaCreate);
        return new ResponseEntity<>(novaTurma, HttpStatus.CREATED); // Status 201
    }

    // Requisição PUT para Atualização dos Dados de uma Turma - ERRADA
    @PutMapping("{idTurma}")
    public ResponseEntity<TurmaDTO> alterarTurma(@RequestBody TurmaDTO turmaDto) { // Passa um Objeto Destacado
        return ResponseEntity.ok(turmaService.alterarTurma(turmaDto));
    }

//    @PutMapping("{id_disciplina}")
//    public DisciplinaDTO alterarDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
//        return disciplinaService.alterarDisciplina(disciplinaDTO);
//    }

    // Requisição DELETE para Exclução de uma Turma
    @DeleteMapping("{idTurma}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Status 204 (Sucesso, mas sem corpo)
    public void removerTurmaPorId(@PathVariable("idTurma") long id) {
        turmaService.removerTurmaPorId(id);
        // Espera receber um Objeto Persistente
    }
}
