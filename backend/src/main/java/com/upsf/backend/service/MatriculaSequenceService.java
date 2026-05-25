package com.upsf.backend.service;

import com.upsf.backend.model.MatriculaSequence;
import com.upsf.backend.repository.MatriculaSequenceRepository;
import com.upsf.backend.util.IdentificacaoUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaSequenceService {

    @Autowired
    private MatriculaSequenceRepository matriculaSequenceRepository;

    @Transactional
    public IdentificacaoUsuario gerarIdentificacao(String codigoBase, String nome){
        MatriculaSequence matriculaSequence = matriculaSequenceRepository.save(new MatriculaSequence());

        String matricula = IdentificacaoUsuarioUtil.createMatricula(codigoBase, matriculaSequence.getId());
        String emailInst = IdentificacaoUsuarioUtil.createEmailInst(nome, matriculaSequence.getId());

        matriculaSequence.setMatricula(matricula);
        matriculaSequenceRepository.save(matriculaSequence);

        return new IdentificacaoUsuario(matricula, emailInst);
    }

    public record IdentificacaoUsuario(String matricula, String emailInst){
    }
}
