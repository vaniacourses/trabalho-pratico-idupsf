package com.upsf.backend.controller;


import com.upsf.backend.create.DiscenteCreate;
import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.dto.HistoricoDTO;
import com.upsf.backend.model.Historico;
import com.upsf.backend.service.HistoricoService;
import com.upsf.backend.update.DiscenteUpdate;
import com.upsf.backend.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/discentes")
public class DiscenteController {
    @Autowired
    private DiscenteService discenteService;

    @Autowired
    private HistoricoService historicoService;

    @PostMapping
    public ResponseEntity<DiscenteDTO> cadastrarDiscente(@RequestBody DiscenteCreate discenteCreate){
        DiscenteDTO novoDiscente = discenteService.cadastrarDiscente(discenteCreate);
        return new ResponseEntity<>(novoDiscente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiscenteDTO>> listarDiscentes(){
        List<DiscenteDTO> discentes = discenteService.listarDiscentes();
        return new ResponseEntity<>(discentes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DiscenteDTO> buscarDiscentePorId(@PathVariable Long id){
        DiscenteDTO discente = discenteService.getDiscenteById(id);
        return ResponseEntity.ok(discente);
    }

    @GetMapping("historico/{id}")
    public ResponseEntity<HistoricoDTO> buscarHistoricoPorDiscente(@PathVariable Long id){
        HistoricoDTO historico = historicoService.buscarHistoricoPorDiscente(id);
        return ResponseEntity.ok(historico);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DiscenteDTO> atualizarDiscente(@PathVariable Long id, @RequestBody DiscenteUpdate discenteUpdate){
        DiscenteDTO discenteAtualizado = discenteService.setDiscenteById(id, discenteUpdate);
        return ResponseEntity.ok(discenteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiscenteDTO> removerDiscente(@PathVariable Long id){
        DiscenteDTO discenteRemovido = discenteService.removeDiscenteById(id);
        return ResponseEntity.ok(discenteRemovido);
    }
}
