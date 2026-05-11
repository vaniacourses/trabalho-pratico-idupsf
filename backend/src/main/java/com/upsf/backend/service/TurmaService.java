package com.upsf.backend.service;

import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> buscarTurmasAtivas() {
        return turmaRepository.findByStatusIgnoreCase("ATIVA");
    }
}