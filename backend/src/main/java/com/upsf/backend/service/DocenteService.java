package com.upsf.backend.service;

import com.upsf.backend.dto.TurmaDTO;
import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.TurmaMapper;
import com.upsf.backend.model.Departamento;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;
import com.upsf.backend.repository.DepartamentoRepository;
import com.upsf.backend.repository.TurmaRepository;
import com.upsf.backend.repository.UsuarioRepository;
import com.upsf.backend.update.DocenteUpdate;
import com.upsf.backend.util.IdentificacaoUsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsf.backend.create.DocenteCreate;
import com.upsf.backend.repository.DocenteRepository;
import com.upsf.backend.mapper.DocenteMapper;
import com.upsf.backend.dto.DocenteDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaMapper turmaMapper;

    public DocenteDTO cadastrarDocente(DocenteCreate docenteCreate) {
        Docente docente = docenteMapper.toEntity(docenteCreate);

        if (usuarioRepository.existsByCpf(docente.getCpf()))
            throw new EntidadeJaExistenteException("CPF já cadastrado");
        if (usuarioRepository.existsByEmail(docente.getEmail()))
            throw new EntidadeJaExistenteException("Email já cadastrado.");

        Departamento dept = departamentoRepository.findById(docenteCreate.idDepartamento())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Departamento não encontrado."));
        String emailInst = IdentificacaoUsuarioUtil.createEmailInst(docente.getNome());
        String matricula = IdentificacaoUsuarioUtil.createMatricula(dept.getCod(), 1L);

        docente.setMatricula(matricula);
        docente.setEmailInst(emailInst);
        docente.setDepartamento(dept);
        docente.setStatus(Usuario.Status.ATIVO);
        docente.setDataIngresso(IdentificacaoUsuarioUtil.getDataHoje());

        docente = docenteRepository.save(docente);

        return docenteMapper.toDto(docente);
    }

    public List<DocenteDTO> listarDocentes(){
        List<Docente> docentes = docenteRepository.findAll();
        return docenteMapper.toDtos(docentes);
    }

    public DocenteDTO getDocenteById(Long id){
        Docente docente = docenteRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Docente de id = " + id + " não encontrado."));
        return docenteMapper.toDto(docente);
    }

    public Docente buscarDocentePorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Docente com id = " + id + " não encontrado."
                ));
    }

    @Transactional(readOnly = true)
    public List<TurmaDTO> listarTurmasDoDocente(Long docenteId) {
        buscarDocentePorId(docenteId);

        return turmaRepository.findByDocenteId(docenteId)
                .stream()
                .map(turmaMapper::toTurmaDTO)
                .toList();
    }

    public DocenteDTO setDocenteById(Long id, DocenteUpdate docenteUpdate){
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Docente de id = " + id + " não encontrado."
                ));

        if (docenteUpdate.email() != null) {
            if (docenteUpdate.email().isBlank()) {
                throw new RegraNegocioException("Email não pode ser vazio.");
            }
            if (!docenteUpdate.email().equals(docente.getEmail())
                    && usuarioRepository.existsByEmail(docenteUpdate.email())) {
                throw new EntidadeJaExistenteException("Email " + docenteUpdate.email() + " já cadastrado.");
            }
            docente.setEmail(docenteUpdate.email());
        }


        if (docenteUpdate.nome() != null) {
            if (docenteUpdate.nome().isBlank()) {
                throw new RegraNegocioException("Nome não pode ser vazio.");
            }
            docente.setNome(docenteUpdate.nome());
        }

        if (docenteUpdate.nomeSocial() != null)
            docente.setNomeSocial(docenteUpdate.nomeSocial());
        if (docenteUpdate.cep() != null)
            docente.setCep(docenteUpdate.cep());
        if (docenteUpdate.logradouro() != null)
            docente.setLogradouro(docenteUpdate.logradouro());
        if (docenteUpdate.genero() != null)
            docente.setGenero(docenteUpdate.genero());
        
        if (docenteUpdate.dataNasc() != null && !docenteUpdate.dataNasc().equals(docente.getDataNasc())) docente.setDataNasc(docenteUpdate.dataNasc());
        if (docenteUpdate.status() != null && !docenteUpdate.status().equals(docente.getStatus())) docente.setStatus(docenteUpdate.status());
        if (docenteUpdate.titulacao() != null && !docenteUpdate.titulacao().equals(docente.getTitulacao())) docente.setTitulacao(docenteUpdate.titulacao());
        if (docenteUpdate.regime() != null && !docenteUpdate.regime().equals(docente.getRegime())) docente.setRegime(docenteUpdate.regime());
        if (docenteUpdate.areasAtuacao() != null && !docenteUpdate.areasAtuacao().equals(docente.getAreasAtuacao())) docente.setAreasAtuacao(docenteUpdate.areasAtuacao());
        if (docenteUpdate.lattes() != null && !docenteUpdate.lattes().equals(docente.getLattes())) docente.setLattes(docenteUpdate.lattes());

        docente = docenteRepository.save(docente);

        return docenteMapper.toDto(docente);
    }

    public DocenteDTO removeDocenteById(Long id){
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new  EntidadeNaoEncontradaException("Docente de id = " + id + "não encontrado"));
        docente.setStatus(Usuario.Status.INATIVO);

        docente = docenteRepository.save(docente);

        return docenteMapper.toDto(docente);
    }
}
