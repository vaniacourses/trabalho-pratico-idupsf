package com.upsf.backend.controller;


import com.upsf.backend.create.CoordenadorCreate;
import com.upsf.backend.dto.CoordenadorDTO;
import com.upsf.backend.update.CoordenadorUpdate;
import com.upsf.backend.service.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {
    @Autowired
    private CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<CoordenadorDTO> cadastrarCoordenador(@RequestBody CoordenadorCreate coordenadorCreate){
        CoordenadorDTO novoCoordenador = coordenadorService.cadastrarCoordenador(coordenadorCreate);
        return new ResponseEntity<>(novoCoordenador, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> listarCoordenadores(){
        List<CoordenadorDTO> coordenadores = coordenadorService.listarCoordenadores();
        return new ResponseEntity<>(coordenadores, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CoordenadorDTO> buscarCoordenadorPorId(@PathVariable Long id){
        CoordenadorDTO coordenador = coordenadorService.getCoordenadorById(id);
        return ResponseEntity.ok(coordenador);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CoordenadorDTO> atualizarCoordenador(@PathVariable Long id, @RequestBody CoordenadorUpdate coordenadorUpdate){
        CoordenadorDTO coordenadorAtualizado = coordenadorService.setCoordenadorById(id, coordenadorUpdate);
        return ResponseEntity.ok(coordenadorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CoordenadorDTO> removerCoordenador(@PathVariable Long id){
        CoordenadorDTO coordenadorRemovido = coordenadorService.removeCoordenadorById(id);
        return ResponseEntity.ok(coordenadorRemovido);
    }


}