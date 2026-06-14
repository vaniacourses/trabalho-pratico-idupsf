package com.upsf.backend.controller;

import com.upsf.backend.dto.*;
import com.upsf.backend.service.DiscenteService;
import com.upsf.backend.service.HistoricoPDFService;
import com.upsf.backend.service.HistoricoService;
import com.upsf.backend.service.RegularidadePDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/documentos")
public class DocumentoController {

    @Autowired
    private HistoricoPDFService historicoPdfService;

    @Autowired
    private RegularidadePDFService regularidadePdfService;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("historico/{id_aluno}")
    public ResponseEntity<byte[]> baixarHistorico(@PathVariable Long id_aluno) {

        DiscenteDTO aluno = discenteService.getDiscenteById(id_aluno);

        Float crAluno = historicoService.buscarCR(id_aluno);

        List<DisciplinaCursadaDTO> disciplinas = historicoService.buscarDisciplinasCursadasDTO(id_aluno);

        RelatorioHistoricoDTO dadosParaPdf = new RelatorioHistoricoDTO(
                aluno.nome(),
                aluno.cpf(),
                aluno.matricula(),
                crAluno,
                disciplinas
        );

        byte[] pdf = historicoPdfService.gerarPdf(dadosParaPdf);


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=historico-" + aluno.matricula() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("regularidade/{id_aluno}")
    public ResponseEntity<byte[]> baixarRegularidade(@PathVariable Long id_aluno) {

        DiscenteDTO aluno = discenteService.getDiscenteById(id_aluno);

        // validação: Apenas alunos ativos podem emitir regularidade de matrícula
        if (aluno.situacaoAcademica() == null || aluno.situacaoAcademica() != com.upsf.backend.model.Discente.SituacaoAcademica.ATIVO) {
            throw new IllegalStateException("O aluno não encontra-se com matrícula ativa para emissão deste documento.");
        }

        RelatorioRegularidadeDTO dadosParaPdf = new RelatorioRegularidadeDTO(
                aluno.nome(),
                aluno.cpf(),
                aluno.matricula(),
                aluno.curso().nome(),
                aluno.periodo()
        );

        byte[] pdf = regularidadePdfService.gerarPdf(dadosParaPdf);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=regularidade-" + aluno.matricula() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}