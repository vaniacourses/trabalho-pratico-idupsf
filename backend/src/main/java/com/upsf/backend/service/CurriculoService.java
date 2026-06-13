package com.upsf.backend.service;

import com.upsf.backend.create.CurriculoCreate;
import com.upsf.backend.create.RegistroDisciplinaCreate;
import com.upsf.backend.dto.CurriculoDTO;
import com.upsf.backend.dto.CursoDTO;
import com.upsf.backend.exception.EntidadeJaExistenteException;
import com.upsf.backend.exception.EntidadeNaoEncontradaException;
import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.mapper.CurriculoMapper;
import com.upsf.backend.mapper.CursoMapper;
import com.upsf.backend.model.Curriculo;
import com.upsf.backend.model.Curso;
import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.RegistroDisciplina;
import com.upsf.backend.repository.CurriculoRepository;
import com.upsf.backend.repository.CursoRepository;
import com.upsf.backend.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurriculoService {

    @Autowired
    CursoService cursoService;
    @Autowired
    CurriculoRepository curriculoRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    CursoMapper cursoMapper;
    @Autowired
    CurriculoMapper curriculoMapper;

    @Transactional
    public CursoDTO criarCurriculo(Long cursoId, CurriculoCreate curriculoCreate, Long departamentoId) {

        if (curriculoRepository.existsByCod(curriculoCreate.cod())) {
            throw new EntidadeJaExistenteException(
                    "Já existe um currículo com o código: " + curriculoCreate.cod()
            );
        }
        cursoService.buscarCursoDoDepartamento(cursoId, departamentoId);

        Curso curso = cursoService.buscarCursoPorId(cursoId);

        Curriculo curriculo = new Curriculo(curriculoCreate.cod());
        curriculo.setCurso(curso);

        if (curriculoCreate.disciplinas() != null && !curriculoCreate.disciplinas().isEmpty()) {
            List<RegistroDisciplina> registros = curriculoCreate.disciplinas().stream()
                    .map(dto -> criarRegistroDisciplina(dto, curriculoCreate.cod()))
                    .toList();
            registros.forEach(curriculo::adicionarDisciplina);
        }

        curso.getCurriculos().add(curriculo);
        cursoRepository.save(curso);

        return cursoMapper.toCursoDTO(curso);
    }

    private RegistroDisciplina criarRegistroDisciplina(RegistroDisciplinaCreate dto, String codigoCurriculo) {
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina com id = " + dto.disciplinaId() +
                        " não encontrada ao montar o currículo '" + codigoCurriculo + "'."
                ));

        return new RegistroDisciplina(dto.tipoCategoria(), dto.periodoRecomendado(), disciplina);
    }

    @Transactional
    public void removerCurriculo(Long cursoId, Long curriculoId, Long departamentoId) {

        cursoService.buscarCursoDoDepartamento(cursoId, departamentoId);

        Curso curso = cursoService.buscarCursoPorId(cursoId);

        Curriculo curriculo = buscarCurriculoDoCurso(curriculoId, cursoId);

        if (curriculo.getCod().equals(curso.getCodCurriculoAtual())) {
            throw new RegraNegocioException(
                    "Não é possível remover o currículo atual do curso."
            );
        }

        curso.getCurriculos().remove(curriculo);
        cursoRepository.save(curso);
    }

    @Transactional
    public CursoDTO definirCurriculoAtual(Long cursoId, Long curriculoId, Long departamentoId) {

        cursoService.buscarCursoDoDepartamento(cursoId, departamentoId);

        Curso curso = cursoService.buscarCursoPorId(cursoId);

        Curriculo curriculo = buscarCurriculoDoCurso(curriculoId, cursoId);

        curso.setCodCurriculoAtual(curriculo.getCod());
        cursoRepository.save(curso);

        return cursoMapper.toCursoDTO(curso);
    }

    public Curriculo buscarCurriculoPorId(Long id) {
        return curriculoRepository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Currículo com id = " + id + " não encontrado."
                        ));
    }

    private Curriculo buscarCurriculoDoCurso(Long curriculoId, Long cursoId) {
        return curriculoRepository.findByIdAndCursoId(curriculoId, cursoId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Currículo com id = " + curriculoId +
                                        " não encontrado no curso de id = " + cursoId + "."
                        ));
    }

    @Transactional(readOnly = true)
    public List<CurriculoDTO> listarPorCurso(Long departamentoId, Long cursoId) {

        cursoService.buscarCursoDoDepartamento(cursoId, departamentoId);

        return curriculoRepository.findAllByCursoId(cursoId)
                .stream()
                .map(curriculoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CurriculoDTO buscarPorId(Long cursoId, Long curriculoId, Long departamentoId) {

        cursoService.buscarCursoDoDepartamento(cursoId, departamentoId);
        Curriculo curriculo = curriculoRepository
                .findByIdAndCursoId(curriculoId, cursoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Currículo não encontrado."
                ));

        return curriculoMapper.toDTO(curriculo);
    }
}
