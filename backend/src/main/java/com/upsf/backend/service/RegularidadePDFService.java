package com.upsf.backend.service;

import com.upsf.backend.dto.RelatorioRegularidadeDTO;
import org.openpdf.text.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RegularidadePDFService extends GeradorDePDFService<RelatorioRegularidadeDTO> {

    @Override
    protected void construirPDF(Document document, RelatorioRegularidadeDTO dados) throws Exception {

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titulo = new Paragraph("Declaração de Regularidade de Matrícula", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(40);
        document.add(titulo);

        Font textoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Font textoBoldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

        Paragraph declaracao = new Paragraph();
        declaracao.setLeading(25f);
        declaracao.setAlignment(Element.ALIGN_JUSTIFIED);
        declaracao.setFont(textoFont);

        declaracao.add("Declaramos para os devidos fins que o(a) discente ");
        declaracao.add(new Chunk(dados.nome(), textoBoldFont));
        declaracao.add(", portador(a) do CPF ");
        declaracao.add(new Chunk(dados.cpf(), textoBoldFont));
        declaracao.add(", matrícula UFF número ");
        declaracao.add(new Chunk(dados.matricula(), textoBoldFont));
        declaracao.add(", encontra-se regularmente matriculado(a) no curso de ");

        String nomeCurso = dados.curso() != null ? dados.curso() : "[CURSO NÃO INFORMADO]";
        declaracao.add(new Chunk(nomeCurso, textoBoldFont));

        declaracao.add(" no período letivo de ");

        String periodo = dados.periodo() != null ? dados.periodo() : "[PERÍODO NÃO INFORMADO]";
        declaracao.add(new Chunk(periodo, textoBoldFont));
        declaracao.add(".");

        document.add(declaracao);

        String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy"));
        Paragraph dataParagraph = new Paragraph("\n\nNiterói, " + dataAtual, textoFont);
        dataParagraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(dataParagraph);
    }
}