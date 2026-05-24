package com.upsf.backend.service;

import com.upsf.backend.create.DiscenteCreate;
import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.DiscenteMapper;
import com.upsf.backend.model.*;
import com.upsf.backend.repository.CursoRepository;
import com.upsf.backend.repository.DiscenteRepository;
import com.upsf.backend.repository.HistoricoRepository;
import com.upsf.backend.repository.UsuarioRepository;
import com.upsf.backend.update.DiscenteUpdate;
import com.upsf.backend.util.IdentificacaoUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private HistoricoRepository historicoRepository;

    public DiscenteDTO cadastrarDiscente(DiscenteCreate discenteCreate) {
        Discente discente =  discenteMapper.toEntity(discenteCreate);

        if (usuarioRepository.existsByCpf(discente.getCpf()))
            throw new EntidadeJaExistenteException("CPF já cadastrado.");
        if (usuarioRepository.existsByEmail(discente.getEmail()))
            throw new EntidadeJaExistenteException("Email já cadastrado.");

        Curso curso = cursoRepository.findById(discenteCreate.idCurso()).orElseThrow(() -> new EntidadeNaoEncontradaException("Curso não encontrado."));
        String matricula = IdentificacaoUsuarioUtil.createMatricula(curso.getCod(), 1L);
        String emailInst = IdentificacaoUsuarioUtil.createEmailInst(discente.getNome());
        Historico hist =  new Historico();
        hist.setDiscente(discente);

        discente.setMatricula(matricula);
        discente.setEmailInst(emailInst);
        discente.setCurso(curso);
        discente.setHistorico(hist);
        discente.setStatus(Usuario.Status.ATIVO);
        discente.setDataIngresso(IdentificacaoUsuarioUtil.getDataHoje());


        discente = discenteRepository.save(discente);

        hist = historicoRepository.save(hist);

        return discenteMapper.toDto(discente);
    }

    public List<DiscenteDTO> listarDiscentes(){
        List<Discente> discentes = discenteRepository.findAll();
        return discenteMapper.toDtos(discentes);
    }

    public DiscenteDTO getDiscenteById(Long id){
        Discente discente = discenteRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Discente de id = " + id + " não encontrado."));
        return discenteMapper.toDto(discente);
    }

    public DiscenteDTO setDiscenteById(Long id, DiscenteUpdate discenteUpdate){
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Discente de id = " + id + "não encontrado."));
        if (discenteUpdate.email() != null) {
            if (discenteUpdate.email().isBlank()) {
                throw new RegraNegocioException("Email não pode ser vazio.");
            }
            if (!discenteUpdate.email().equals(discente.getEmail()) && usuarioRepository.existsByEmail(discenteUpdate.email())) {
                throw new EntidadeJaExistenteException("Email ja cadastrado.");
            }
            discente.setEmail(discenteUpdate.email());
        }

        if (discenteUpdate.nome() != null && !discenteUpdate.nome().equals(discente.getNome())) {
            if (discenteUpdate.nome().isBlank()) {
                throw new RegraNegocioException("Nome não pode ser vazio.");
            }
            discente.setNome(discenteUpdate.nome());
        }
        if (discenteUpdate.dataNasc() != null && !discenteUpdate.dataNasc().equals(discente.getDataNasc()))
            discente.setDataNasc(discenteUpdate.dataNasc());
        if (discenteUpdate.status() != null && !discenteUpdate.status().equals(discente.getStatus()))
            discente.setStatus(discenteUpdate.status());
        if (discenteUpdate.periodo() != null && !discenteUpdate.periodo().equals(discente.getPeriodo()))
            discente.setPeriodo(discenteUpdate.periodo());
        if (discenteUpdate.situacaoAcademica() != null && !discenteUpdate.situacaoAcademica().equals(discente.getSituacaoAcademica()))
            discente.setSituacaoAcademica(discenteUpdate.situacaoAcademica());
        if (discenteUpdate.formaPermanencia() != null && !discenteUpdate.formaPermanencia().equals(discente.getFormaPermanencia()))
            discente.setFormaPermanencia(discenteUpdate.formaPermanencia());

        discente = discenteRepository.save(discente);

        return discenteMapper.toDto(discente);
    }

    public DiscenteDTO removeDiscenteById(Long id){
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new  EntidadeNaoEncontradaException("Discente de id = " + id + "não encontrado"));
        discente.setStatus(Usuario.Status.INATIVO);

        discente = discenteRepository.save(discente);

        return discenteMapper.toDto(discente);
    }
}
