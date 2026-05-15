package com.upsf.backend.service;

import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.repository.DiscenteRepository;
import com.upsf.backend.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DocenteMapper docenteMapper;

    public DocenteDTO castrarDocente(DocenteCreate docenteCreate) {

    }
}