package com.upsf.backend.controller;


import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.dto.DocenteDTO;
import com.upsf.backend.update.DocenteUpdate;
import com.upsf.backend.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/docentes")
public class DocenteController {
    @Autowired
    private DocenteService docenteService;

    @PostMapping
    public ResponseEntity<DocenteDTO> cadastrarDocente(@RequestBody DocenteCreate docenteCreate){
        DocenteDTO novoDocente = docenteService.cadastrarDocente(docenteCreate);
        return new ResponseEntity<>(novoDocente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> listarDocentes(){
        List<DocenteDTO> docentes = docenteService.listarDocentes();
        return new ResponseEntity<>(docentes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DocenteDTO> buscarDocentePorId(@PathVariable Long id){
        DocenteDTO docente = docenteService.getDocenteById(id);
        return ResponseEntity.ok(docente);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DocenteDTO> atualizarDocente(@PathVariable Long id, @RequestBody DocenteUpdate docenteUpdate){
        DocenteDTO docenteAtualizado = docenteService.setDocenteById(id, docenteUpdate);
        return ResponseEntity.ok(docenteAtualizado);
    }
}
