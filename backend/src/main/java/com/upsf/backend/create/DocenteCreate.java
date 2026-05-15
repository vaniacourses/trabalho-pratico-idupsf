package com.upsf.backend.create;

import com.upsf.backend.model.Docente;

import java.util.Date;
import java.util.List;

// Dependência de @Null ainda inexistente
public record DocenteCreate(
        String nome,
        String email,
        String cpf,
        String senha,
        Date dataNasc,
        Docente.Titulacao titulo,
        Docente.Regime regime, // eu não sei oq é esse regime, então deixei aqui
        List<String> areasAtuacao,
        String lattes,
        Long idDepartamento
) {

}