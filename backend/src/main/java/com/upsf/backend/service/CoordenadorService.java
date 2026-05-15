package com.upsf.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsf.backend.create.CoordenadorCreate;
import com.upsf.backend.dto.CoordenadorDTO;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadoorMapper coordenadorMapper;

    public CoordenadorDTO cadastrarCoordenador(CoordenadorCreate coordenadorCreate){

    }
}