package com.upsf.backend.service;

import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.model.*;
import com.upsf.backend.repository.CursoRepository;
import com.upsf.backend.repository.DepartamentoRepository;
import com.upsf.backend.repository.UsuarioRepository;
import com.upsf.backend.update.CoordenadorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.upsf.backend.create.CoordenadorCreate;
import com.upsf.backend.dto.CoordenadorDTO;
import com.upsf.backend.repository.CoordenadorRepository;
import com.upsf.backend.mapper.CoordenadorMapper;
import com.upsf.backend.util.IdentificacaoUsuarioUtil;

import java.util.List;

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
        String matricula = IdentificacaoUsuarioUtil.createMatricula(curso.getCod(), 1L);

        coordenador.setMatricula(matricula);
        coordenador.setEmailInst(email);
        coordenador.setCurso(curso);
        coordenador.setDepartamento(dept);
        coordenador.setStatus(Usuario.Status.ATIVO);
        coordenador.setDataIngresso(IdentificacaoUsuarioUtil.getDataHoje());
        coordenador.setInicioMandato(IdentificacaoUsuarioUtil.getDataHoje());

        coordenadorRepository.save(coordenador);

        return coordenadorMapper.toDto(coordenador);
    }

    public List<CoordenadorDTO> listarCoordenadores(){
        List<Coordenador> coordenadores = coordenadorRepository.findAll();
        return coordenadorMapper.toDtos(coordenadores);
    }

    public CoordenadorDTO getCoordenadorById(Long id){
        Coordenador coordenador = coordenadorRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Coordenador de id = " + id + " não encontrado."));
        return coordenadorMapper.toDto(coordenador);
    }

    public CoordenadorDTO setCoordenadorById(Long id, CoordenadorUpdate coordenadorUpdate){
        Coordenador coordenador = coordenadorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Coordenador de id =" + id + "não emcontrado."));

        if (coordenadorUpdate.email() != null) {
            if (coordenadorUpdate.email().isBlank()) {
                throw new RegraNegocioException("Email não pode ser vazio.");
            }
            if (!coordenadorUpdate.email().equals(coordenador.getEmail()) && usuarioRepository.existsByEmail(coordenadorUpdate.email())) {
                throw new EntidadeJaExistenteException("Email ja cadastrado.");
            }
            coordenador.setEmail(coordenadorUpdate.email());
        }

        if (coordenadorUpdate.nome() != null) {
            if (coordenadorUpdate.nome().isBlank()) {
                throw new RegraNegocioException("Nome não pode ser vazio.");
            }
            coordenador.setNome(coordenadorUpdate.nome());
        }

        if (coordenadorUpdate.nomeSocial() != null)
            coordenador.setNomeSocial(coordenadorUpdate.nomeSocial());
        if (coordenadorUpdate.cep() != null)
            coordenador.setCep(coordenadorUpdate.cep());
        if (coordenadorUpdate.logradouro() != null)
            coordenador.setLogradouro(coordenadorUpdate.logradouro());
        if (coordenadorUpdate.genero() != null)
            coordenador.setGenero(coordenadorUpdate.genero());
        
        if (coordenadorUpdate.dataNasc() != null && !coordenadorUpdate.dataNasc().equals(coordenador.getDataNasc()))
            coordenador.setDataNasc(coordenadorUpdate.dataNasc());
        if (coordenadorUpdate.status() != null && !coordenadorUpdate.status().equals(coordenador.getStatus()))
            coordenador.setStatus(coordenadorUpdate.status());
        if (coordenadorUpdate.titulacao() != null &&  !coordenadorUpdate.titulacao().equals(coordenador.getTitulacao()))
            coordenador.setTitulacao(coordenadorUpdate.titulacao());
        if (coordenadorUpdate.regime() != null &&  !coordenadorUpdate.regime().equals(coordenador.getRegime()))
            coordenador.setRegime(coordenadorUpdate.regime());
        if (coordenadorUpdate.areasAtuacao() != null && !coordenadorUpdate.areasAtuacao().equals(coordenador.getAreasAtuacao()))
            coordenador.setAreasAtuacao(coordenadorUpdate.areasAtuacao());
        if (coordenadorUpdate.lattes() != null &&   !coordenadorUpdate.lattes().equals(coordenador.getLattes()))
            coordenador.setLattes(coordenadorUpdate.lattes());

        coordenador = coordenadorRepository.save(coordenador);

        return coordenadorMapper.toDto(coordenador);
    }

    public CoordenadorDTO removeCoordenadorById(Long id){
        Coordenador coordenador = coordenadorRepository.findById(id)
                .orElseThrow(() -> new  EntidadeNaoEncontradaException("Coordenador de id = " + id + "não encontrado"));
        coordenador.setStatus(Usuario.Status.INATIVO);
        coordenador.setFimMandato(IdentificacaoUsuarioUtil.getDataHoje());

        coordenador = coordenadorRepository.save(coordenador);

        return coordenadorMapper.toDto(coordenador);
    }
}
