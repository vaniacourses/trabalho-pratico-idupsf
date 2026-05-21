package com.upsf.backend.controller;

import com.upsf.backend.create.InscricaoCreate;
import com.upsf.backend.dto.InscricaoDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.service.InscricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscricoes") // caminhos aqui são placeholders
@RequiredArgsConstructor
public class InscricaoController {

    private final InscricaoService inscricaoService;

    // tela de inscrição pode ter inscricões feitas e turmas disponiveis
    // o aluno pode escolher as turmas na pagina turmas disponiveis
    @GetMapping("/turmas-disponiveis/{discenteId}")
    public List<TurmaDTO> listarTurmasDisponiveis(@PathVariable Long discenteId) {
        return inscricaoService.listarTurmasDisponiveis(discenteId);
    }

    @PostMapping
    public List<InscricaoDTO> realizarInscricao(@RequestBody InscricaoCreate dto) {
        return  inscricaoService.realizarInscricao(dto);
    }

    // isso aqui está errado o delete não aceita dtos
//    @DeleteMapping("/{id}/cancelar")
//    public void cancelarInscricao(@RequestBody InscricaoRequestDTO dto) {
//        inscricaoService.cancelarInscricao(dto);
//    }

    // nova versão
    @DeleteMapping("/cancelar")
    public void cancelarInscricao(@RequestParam Long discenteId,
                                  @RequestParam List<Long> turmasIds) {
        inscricaoService.cancelarInscricao(discenteId, turmasIds);
    }
}
