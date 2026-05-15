package com.upsf.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsf.backend.create.CoordenadorCreate;
import com.upsf.backend.dto.CoordenadorDTO;
import com.upsf.backend.repository.CoordenadorRepository;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadoorMapper coordenadorMapper;

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    public CoordenadorDTO cadastrarCoordenador(CoordenadorCreate coordenadorCreate){

    }
}