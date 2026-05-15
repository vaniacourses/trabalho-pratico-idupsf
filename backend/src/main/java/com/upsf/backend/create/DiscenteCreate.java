package com.upsf.backend.create;

import com.upsf.backend.model.Discente;

import java.util.Date;
import java.util.List;

// Dependência de @Null ainda inexistente
public record DiscenteCreate(
        String nome,
        String email,
        String cpf,
        String senha,
        Date dataNasc,
        Long idCurso,
        Discente.FormaPermanencia permanencia //não sei oq seria isso, então estou deixando aqui
) {

}