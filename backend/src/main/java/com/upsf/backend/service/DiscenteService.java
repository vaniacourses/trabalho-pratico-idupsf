package com.upsf.backend.service;

import com.upsf.backend.create.DiscenteCreate;
import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.mapper.DiscenteMapper;
import com.upsf.backend.model.*;
import com.upsf.backend.repository.CursoRepository;
import com.upsf.backend.repository.DiscenteRepository;
import com.upsf.backend.repository.UsuarioRepository;
import com.upsf.backend.util.IdentificacaoUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DiscenteMapper discenteMapper;
    @Autowired
    private CursoRepository cursoRepository;

    public DiscenteDTO buscarPorId(Long id_discente) {
        Discente discente = discenteRepository.findById(id_discente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Discente de id = " + id_discente + " não encontrado."
                ));
        return discenteMapper.toDto(discente);
    }

    public DiscenteDTO cadastrarDiscente(DiscenteCreate discenteCreate) {
        Discente discente =  discenteMapper.toEntity(discenteCreate);

        if (usuarioRepository.existsByCpf(discente.getCpf()))
            throw new EntidadeJaExistenteException("CPF já cadastrado.");
        if (usuarioRepository.existsByEmail(discente.getEmail()))
            throw new EntidadeJaExistenteException("Email já cadastrado.");

        Curso curso = cursoRepository.findById(discenteCreate.idCurso()).orElseThrow(() -> new EntidadeNaoEncontradaException("Curso não encontrado."));
        String matricula = IdentificacaoUsuarioUtil.createMatricula(curso.getCod(), 1L);
        String emailInst = IdentificacaoUsuarioUtil.createEmailInst(discente.getNome());

        discente.setMatricula(matricula);
        discente.setEmailInst(emailInst);
        discente.setCurso(curso);
        discente.setHistorico(new Historico());
        discente.setStatus(Usuario.Status.ATIVO);
        discente.setDataIngresso(IdentificacaoUsuarioUtil.getDataHoje());
        discente.setCodCurriculo(curso.getCodCurriculoAtual());

        discente = discenteRepository.save(discente);

        return discenteMapper.toDto(discente);
    }

}

