package com.upsf.backend.inscricao;

import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Turma;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidarDisciplinasFeitas implements ValidacaoInscricao{

    @Override
    public void validar(Discente discente, List<Turma> turmasSelecionadas) {
        throw new RegraNegocioException("A turma " + n.getDisciplina().getNome() + " conflita com suas disciplinas já cursadas.");
    }
}
