package com.upsf.backend.service;


import com.upsf.backend.dto.DiscenteDTO;
import com.upsf.backend.dto.HistoricoDTO;
import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoPDFService extends GeradorDePDF<DiscenteDTO>{

    @Autowired
    HistoricoService historicoService;

    @Override
    protected void construirPDF(Document document, DiscenteDTO dados) throws Exception {

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titulo = new Paragraph("Histórico Escolar", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(30);
        document.add(titulo);
        document.add(new Paragraph("Nome do Aluno: " + dados.nome()));
        document.add(new Paragraph("CPF: " + dados.cpf()));
        document.add(new Paragraph("Matrícula UFF: " + dados.matricula()));

        PdfPTable tabela = new PdfPTable(6);
        tabela.setWidthPercentage(100);
        tabela.setSpacingBefore(15);

        tabela.addCell(new PdfPCell(new Paragraph("Código da Disciplina", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Nome da Disciplina", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Nota", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("VS", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Horas ", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        tabela.addCell(new PdfPCell(new Paragraph("Período", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));

        // assumindo que isso retorna uma lista de HistoricoDTO
        List<HistoricoDTO> historico = historicoService.buscarDisciplinasConcluidas(dados.id());

    }
}
