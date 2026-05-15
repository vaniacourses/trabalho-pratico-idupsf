package com.upsf.backend.service;

import com.upsf.backend.dto.DisciplinaCursadaDTO;
import com.upsf.backend.dto.RelatorioHistoricoDTO;
import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;

@Service
public class HistoricoPDFService extends GeradorDePDFService<RelatorioHistoricoDTO> {

    @Override
    protected void construirPDF(Document document, RelatorioHistoricoDTO dados) throws Exception {

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titulo = new Paragraph("Histórico Escolar", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(30);
        document.add(titulo);

        document.add(new Paragraph("Nome do Aluno: " + dados.nome()));
        document.add(new Paragraph("CPF: " + dados.cpf()));
        document.add(new Paragraph("Matrícula UFF: " + dados.matricula()));

        String crFormatado = dados.cr() != null ? String.format("%.2f", dados.cr()) : "0.00";
        document.add(new Paragraph("CR: " + crFormatado));

        PdfPTable tabela = new PdfPTable(7);
        tabela.setWidthPercentage(100);
        tabela.setSpacingBefore(15);

        tabela.setWidths(new float[]{2f, 4f, 1.5f, 1.5f, 1.5f, 2f, 2.5f});

        tabela.addCell(new PdfPCell(new Paragraph("Código", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Disciplina", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Nota", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("VS", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Horas", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Período", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Situação", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));

        for (DisciplinaCursadaDTO disc : dados.disciplinasCursadas()) {
            tabela.addCell(disc.codigoDisciplina() != null ? disc.codigoDisciplina() : "-");
            tabela.addCell(disc.nomeDisciplina() != null ? disc.nomeDisciplina() : "-");
            tabela.addCell(disc.nota() != null ? String.valueOf(disc.nota()) : "-");

            // Regra visual: Se a nota da VS for nula ou 0.0, exibe um traço para manter a tabela limpa
            String textoVS = (disc.notaVS() != null && disc.notaVS() > 0.0f) ? String.valueOf(disc.notaVS()) : "-";
            tabela.addCell(textoVS);

            tabela.addCell(disc.cargaHoraria() != null ? String.valueOf(disc.cargaHoraria()) : "-");
            tabela.addCell(disc.anoSemestre() != null ? disc.anoSemestre() : "-");
            tabela.addCell(disc.status() != null ? disc.status() : "-");
        }

        document.add(tabela);
    }
}