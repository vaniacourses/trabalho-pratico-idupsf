package com.upsf.backend.controller;


import com.upsf.backend.create.CoordenadorCreate;
import com.upsf.backend.dto.CoordenadorDTO;
import com.upsf.backend.service.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}