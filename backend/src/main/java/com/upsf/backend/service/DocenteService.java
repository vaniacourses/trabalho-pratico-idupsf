package com.upsf.backend.service;

import com.upsf.backend.model.Docente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.repository.DocenteRepository;
import com.upsf.backend.mapper.DocenteMapper;
import com.upsf.backend.dto.DocenteDTO;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DocenteMapper docenteMapper;

    public DocenteDTO cadastrarDocente(DocenteCreate docenteCreate) {
        Docente docente = docenteMapper.toEntity(docenteCreate);

        docente = docenteRepository.save(docente);

        return docenteMapper.toDto(docente);
    }
}