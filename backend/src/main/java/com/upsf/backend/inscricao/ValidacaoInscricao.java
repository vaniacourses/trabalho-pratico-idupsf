package com.upsf.backend.inscricao;

import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Turma;
import java.util.List;

public interface ValidacaoInscricao {
    void validar(Discente discente, List<Turma> turmasSelecionadas);
}