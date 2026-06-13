package com.upsf.backend.spec;

import com.upsf.backend.model.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TurmaSpec {

    public static Specification<Turma> comFiltros(
            String nomeCodDisciplina,
            String nomeDocente,
            String departamento,
            String anoSemestre
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Joins explícitos
            Join<Turma, Disciplina> disciplinaJoin = root.join("disciplina", JoinType.LEFT);
            Join<Turma, Docente> docenteJoin = root.join("docente", JoinType.LEFT);
            Join<Docente, Departamento> departamentoJoin = docenteJoin.join("departamento", JoinType.LEFT);

            // Busca por nome OU código da disciplina
            if (nomeCodDisciplina != null && !nomeCodDisciplina.isBlank()) {
                String termo = "%" + nomeCodDisciplina.toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(disciplinaJoin.get("nome")), termo),
                        cb.like(cb.lower(disciplinaJoin.get("cod")), termo)
                ));
            }

            // Busca parcial por nome do docente
            if (nomeDocente != null && !nomeDocente.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(docenteJoin.get("nome")),
                        "%" + nomeDocente.toLowerCase() + "%"
                ));
//                Join<Docente, Usuario> usuarioJoin = docenteJoin.join("id", JoinType.LEFT);
//                predicates.add(cb.like(
//                        cb.lower(usuarioJoin.get("nome")),
//                        "%" + nomeDocente.toLowerCase() + "%"
//                ));
            }



            // Busca parcial por nome/cod do departamento
            if (departamento != null && !departamento.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(departamentoJoin.get("cod")),
                        "%" + departamento.toLowerCase() + "%"
                ));
            }

            // Busca exata por ano/semestre
            if (anoSemestre != null && !anoSemestre.isBlank()) {
                predicates.add(cb.equal(root.get("anoSemestre"), anoSemestre));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}