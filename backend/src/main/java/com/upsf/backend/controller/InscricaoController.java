package com.upsf.backend.controller;

import com.upsf.backend.dto.InscricaoRequestDTO;
import com.upsf.backend.dto.InscricaoResponseDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscricoes") // caminhos aqui são placeholders
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    // tela de inscrição pode ter inscricões feitas e turmas disponiveis
    // o aluno pode escolher as turmas na pagina turmas disponiveis
    @GetMapping("/turmas-disponiveis/{discenteId}")
    public List<TurmaDTO> listarTurmasDisponiveis(@PathVariable Long discenteId) {
        return inscricaoService.listarTurmasDisponiveis(discenteId);
    }

    @PostMapping
    public List<InscricaoResponseDTO> realizarInscricao(@RequestBody InscricaoRequestDTO dto) {
        return  inscricaoService.realizarInscricao(dto);
    }

    @DeleteMapping("/{id}/cancelar")
    public void cancelarInscricao(@RequestBody InscricaoRequestDTO dto) {
        inscricaoService.cancelarInscricao(dto);
    }
}
