package com.upsf.backend.controller;


import com.upsf.backend.create.DiscenteCreate;
import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.service.DiscenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/docentes")
public class DocenteController {
    @PostMapping
    public ResponseEntity<DocenteDTO> cadastrarDocente(@RequestBody DocenteCreate docenteCreate){
        DiscenteDTO novoDocente = DocenteService.cadastrarDocente(docenteCreate);
        return new ResponseEntity<>(novoDocente, HttpStatus.CREATED);
    }
}
