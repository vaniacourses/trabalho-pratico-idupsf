package com.upsf.backend.service;

import com.upsf.backend.dto.DepartamentoDTO;
import com.upsf.backend.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional(readOnly = true)
    public List<DepartamentoDTO> listarTodos() {
        return departamentoRepository.findAll()
                .stream()
                .map(d -> new DepartamentoDTO(d.getId(), d.getCod(), d.getNome(), d.getEndereco(), d.getCampus()))
                .toList();
    }
}
