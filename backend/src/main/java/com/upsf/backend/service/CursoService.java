package com.upsf.backend.service;

import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.CursoMapper;
import com.upsf.backend.model.Curso;
import com.upsf.backend.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {
    @Autowired
    private final CursoRepository cursoRepository;
    @Autowired
    private final CursoMapper cursoMapper;

}