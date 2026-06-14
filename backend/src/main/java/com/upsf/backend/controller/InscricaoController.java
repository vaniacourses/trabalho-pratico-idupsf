package com.upsf.backend.controller;

import com.upsf.backend.create.InscricaoCreate;
import com.upsf.backend.dto.InscricaoDTO;
import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.model.Inscricao;
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

    @GetMapping("/inscrições-feitas/{discenteId}")
    public List<InscricaoDTO> listarInscricoesFeitasPorId(@PathVariable Long discenteId) {
        return inscricaoService.listarInscricoesFeitasPorId(discenteId);
    }

    @PostMapping
    public List<InscricaoDTO> realizarInscricao(@RequestBody InscricaoCreate dto) {
        return  inscricaoService.realizarInscricao(dto);
    }

    @DeleteMapping("/cancelar")
    public void cancelarInscricao(@RequestParam Long discenteId,
                                  @RequestParam List<Long> turmasIds) {
        inscricaoService.cancelarInscricao(discenteId, turmasIds);
    }
//    @PostMapping
//    public InscricaoResponseDTO realizarInscricao(@RequestBody InscricaoRequestDTO dto) {
//        return  inscricaoService.realizarInscricao(dto);
//    }
//
//    @DeleteMapping("/{id}/cancelar")
//    public InscricaoResponseDTO cancelarInscricao(@PathVariable Long id) {
//        return inscricaoService.cancelarInscricao(id);
//    }

    // Requisição de Listar todas as Inscrições Por Turma
    @GetMapping("/turma-inscricoes/{turmaId}")
    public List<Inscricao> listarInscricoesPorTurma(@PathVariable Long turmaId) {
        return inscricaoService.listarInscricoesPorTurma(turmaId);
    }

    // Requisição de Deletar todas as Inscrições Por Turma
    // (Requisição de Teste - é usada em TurmaService)
    // Mantém consistência de dados após Deletar uma Turma
    @DeleteMapping("/turma-inscricoes/{turmaId}")
    public void deletarInscricoesPorTurma(@PathVariable Long turmaId) {
        inscricaoService.deletarInscricoesPorTurma(turmaId);
    }

}
