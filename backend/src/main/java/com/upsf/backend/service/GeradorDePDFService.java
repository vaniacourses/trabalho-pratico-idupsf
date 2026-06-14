package com.upsf.backend.service;

import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public abstract class GeradorDePDFService<T> {

    public byte[] gerarPdf(T dados) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(document, outputStream);

            document.open();

            adicionarCabecalhoPadrao(document);

            construirPDF(document, dados);

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o PDF", e);
        }
    }

    protected void adicionarCabecalhoPadrao(Document document) throws IOException {

        ClassPathResource imgFile = new ClassPathResource("logo_idUPSF.jpg");
        if (imgFile.exists()) {
            Image logo = Image.getInstance(imgFile.getURL());
            logo.scaleToFit(120, 120);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
        }

    }

    protected void adicionarRodape(Document document) throws Exception {}

    protected abstract void construirPDF(Document document, T dados) throws Exception;

}

