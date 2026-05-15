package com.upsf.backend.util;

import java.time.LocalDate;

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

    public static String formatarDataParaAnoSemestre(LocalDate dataIngresso)  {

    }

    public static String createMatricula(String cursoCod, Long userId){
        LocalDate dataIngresso =  LocalDate.now();
        String anoSemestreAdmissao = IdentificacaoUsuarioUtil.formatarDataParaAnoSemestre(dataIngresso);

        StringBuilder matricula = new StringBuilder();
        matricula.append(anoSemestreAdmissao);
        matricula.append(cursoCod);
        matricula.append(userId.toString());

        return matricula.toString().toLowerCase();
    }
}
