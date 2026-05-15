package com.upsf.backend.service;

import com.upsf.backend.create.DiscenteCreate;
import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.DiscenteMapper;
import com.upsf.backend.model.Discente;
import com.upsf.backend.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private DiscenteMapper discenteMapper;

    public DiscenteDTO buscarPorId(Long id_discente) {
        Discente discente = discenteRepository.findById(id_discente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Discente de id = " + id_discente + " não encontrado."
                ));
        return discenteMapper.toDiscenteDTO(discente);
    }

    public DiscenteDTO castrarDiscente(DiscenteCreate discenteCreate) {

    }

}
