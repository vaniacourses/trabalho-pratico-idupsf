package com.upsf.backend.inscricao;

import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Inscricao;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.InscricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidarConfrontoHorarios implements ValidacaoInscricao{
    private final InscricaoRepository inscricaoRepository;

    @Override
    public void validar(Discente discente, List<Turma> novas) {
        // Checa conflito entre as turmas selecionadas agora
        checarConflitoTurmaNaLista(novas);

        // Checa conflito com inscrições já confirmadas do aluno
        checarConflitoInscricaoExistente(discente, novas);
    }

    private void checarConflitoTurmaNaLista(List<Turma> novas) {
        for (int i = 0; i < novas.size(); i++) {
            for (int j = i + 1; j < novas.size(); j++) {
                if (novas.get(i).getHorario().temConflitoCom(novas.get(j).getHorario())) {
                    throw new RegraNegocioException("Conflito de horário entre " +
                            novas.get(i).getDisciplina().getNome() + " e " + novas.get(j).getDisciplina().getNome());
                }
            }
        }
    }

    private void checarConflitoInscricaoExistente(Discente discente, List<Turma> novas) {
        List<Inscricao> atuais = inscricaoRepository.findByDiscenteId(discente.getId());
        for (Turma n : novas) {
            for (Inscricao a : atuais) {
                if (n.getHorario().temConflitoCom(a.getTurma().getHorario())) {
                    throw new RegraNegocioException("A turma " + n.getDisciplina().getNome() + " conflita com sua grade atual.");
                }
            }
        }
    }
}
