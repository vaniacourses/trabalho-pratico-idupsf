package com.upsf.backend.service;

import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;


//    public List<DepartamentoDTO> listarOptions() {
//        return departamentoRepository.findAllAsOptions();
//    }
}
