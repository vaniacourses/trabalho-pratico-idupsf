package com.upsf.backend.service;

import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.model.Coordenador;
import com.upsf.backend.model.Curso;
import com.upsf.backend.model.Departamento;
import com.upsf.backend.model.Usuario;
import com.upsf.backend.repository.CursoRepository;
import com.upsf.backend.repository.DepartamentoRepository;
import com.upsf.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.upsf.backend.create.CoordenadorCreate;
import com.upsf.backend.dto.CoordenadorDTO;
import com.upsf.backend.repository.CoordenadorRepository;
import com.upsf.backend.mapper.CoordenadorMapper;
import com.upsf.backend.util.IdentificacaoUsuarioUtil;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadorMapper coordenadorMapper;
    @Autowired
    private CoordenadorRepository coordenadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;


    public CoordenadorDTO cadastrarCoordenador(CoordenadorCreate coordenadorCreate){
        Coordenador coordenador = coordenadorMapper.toEntity(coordenadorCreate);

        if (usuarioRepository.existsByCpf(coordenador.getCpf()))
            throw new EntidadeJaExistenteException("CPF ja cadastrado.");
        if (usuarioRepository.existsByEmail(coordenador.getEmail()))
            throw new EntidadeJaExistenteException("Email ja cadastrado.");

        Curso curso = cursoRepository.findById(coordenadorCreate.idCurso()).orElseThrow(() -> new EntidadeNaoEncontradaException("Curso não encontrado."));
        Departamento dept = departamentoRepository.findById(coordenadorCreate.idDepartamento()).orElseThrow(() -> new EntidadeNaoEncontradaException("Departamento não encontrado."));
        String email = IdentificacaoUsuarioUtil.createEmailInst(coordenador.getNome());
        String matricula = IdentificacaoUsuarioUtil.createMatricula(curso.getCod(), coordenador.getId());

        coordenador.setMatricula(matricula);
        coordenador.setEmailInst(email);
        coordenador.setCurso(curso);
        coordenador.setDepartamento(dept);
        coordenador.setStatus(Usuario.Status.ATIVO);
        coordenador.setDataIngresso(IdentificacaoUsuarioUtil.getDataHoje());

        coordenadorRepository.save(coordenador);

        return coordenadorMapper.toDto(coordenador);
    }
}