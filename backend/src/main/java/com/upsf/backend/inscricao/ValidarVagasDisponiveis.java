package com.upsf.backend.inscricao;

import com.upsf.backend.exception.RegraNegocioException;
import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Turma;
import com.upsf.backend.repository.InscricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ValidarVagasDisponiveis implements ValidacaoInscricao {
    private static final String STATUS_INSCRICAO_ATIVA = "ATIVA";

    private final InscricaoRepository inscricaoRepository;

    @Override
    public void validar(Discente discente, List<Turma> turmasSelecionadas) {
        Map<Long, Long> quantidadeSolicitadaPorTurma = turmasSelecionadas.stream()
                .collect(Collectors.groupingBy(Turma::getId, Collectors.counting()));

        for (Turma turma : turmasSelecionadas) {
            long inscricoesAtivas = inscricaoRepository.countByTurmaIdAndStatus(
                    turma.getId(),
                    STATUS_INSCRICAO_ATIVA
            );
            long quantidadeSolicitada = quantidadeSolicitadaPorTurma.get(turma.getId());

            if (inscricoesAtivas + quantidadeSolicitada > turma.getMaxAlunos()) {
                throw new RegraNegocioException("A turma " + turma.getCod() + " não possui vagas disponíveis.");
            }
        }
    }
}
