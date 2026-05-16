package com.upsf.backend.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IdentificacaoUsuarioUtil {
    public static String createEmailInst(String nome) {
        String[] partes = nome.split("\\s+");
        StringBuilder email = new StringBuilder();

        if (partes.length > 0) {
            email.append(partes[0]);
            for (int i = 1; i < partes.length; i++) {
                if (!partes[i].isEmpty()){
                    email.append(partes[i].charAt(0));
                }
            }
            email.append("@id.ufps.com.br");
        }
        return email.toString().toLowerCase();
    }

    public static LocalDate getDataHoje(){
        return LocalDate.now();
    }

    public static String formatarDataParaAnoSemestre(LocalDate dataIngresso)  {
        String semestre = "";
        int mes = dataIngresso.getMonthValue();
        StringBuilder anoSemestre = new StringBuilder();

        if (mes <= 6)  semestre = "1";
        else semestre = "2";

        anoSemestre.append(DateTimeFormatter.ofPattern("yy"));
        anoSemestre.append(semestre);

        return anoSemestre.toString();
    }

    public static String createMatricula(String cursoCod, Long userId){
        LocalDate dataIngresso =  IdentificacaoUsuarioUtil.getDataHoje();
        String anoSemestreAdmissao = IdentificacaoUsuarioUtil.formatarDataParaAnoSemestre(dataIngresso);

        StringBuilder matricula = new StringBuilder();
        matricula.append(anoSemestreAdmissao);
        matricula.append(cursoCod);
        matricula.append(userId.toString());

        return matricula.toString().toLowerCase();
    }
}
