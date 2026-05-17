package com.upsf.backend.controller;


import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.dto.DocenteDTO;
import com.upsf.backend.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
