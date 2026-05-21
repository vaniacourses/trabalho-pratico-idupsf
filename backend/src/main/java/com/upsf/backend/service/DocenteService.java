package com.upsf.backend.service;

import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.model.Departamento;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;
import com.upsf.backend.repository.DepartamentoRepository;
import com.upsf.backend.repository.UsuarioRepository;
import com.upsf.backend.util.IdentificacaoUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.repository.DocenteRepository;
import com.upsf.backend.mapper.DocenteMapper;
import com.upsf.backend.dto.DocenteDTO;

@Service
public class DocenteService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DocenteMapper docenteMapper;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public DocenteDTO cadastrarDocente(DocenteCreate docenteCreate) {
        Docente docente = docenteMapper.toEntity(docenteCreate);

        if (usuarioRepository.existsByCpf(docente.getCpf()))
            throw new EntidadeJaExistenteException("CPF já cadastrado");
        if (usuarioRepository.existsByEmail(docente.getEmail()))
            throw new EntidadeJaExistenteException("Email já cadastrado.");

        Departamento dept = departamentoRepository.findById(docenteCreate.idDepartamento()).orElseThrow(() -> new EntidadeNaoEncontradaException("Departamento não encontrado."));
        String emailInst = IdentificacaoUsuarioUtil.createEmailInst(docente.getNome());
        String matricula = IdentificacaoUsuarioUtil.createMatricula("", 1L);

        docente.setMatricula(matricula);
        docente.setEmailInst(emailInst);
        docente.setDepartamento(dept);
        docente.setStatus(Usuario.Status.ATIVO);
        docente.setDataIngresso(IdentificacaoUsuarioUtil.getDataHoje());

        docente = docenteRepository.save(docente);

        return docenteMapper.toDto(docente);
    }
}