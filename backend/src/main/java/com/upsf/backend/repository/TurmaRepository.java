package com.upsf.backend.repository;

import com.upsf.backend.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    // Consulta das Turmas Ativas com Disciplina e Horário (Para Quadro de Horários)
    @Query("SELECT t FROM Turma t JOIN FETCH t.disciplina " +
            "JOIN FETCH t.horario WHERE t.status = 'ATIVA'")
    List<Turma> buscarTurmasAtivas();


    // Consulta das Turmas Ativas com Pré-Requisitos (Para InscricaoService)
    @Query("SELECT t FROM Turma t JOIN FETCH t.disciplina d " +
            "LEFT JOIN FETCH d.preRequisitos WHERE t.status = 'ATIVA'")
    List<Turma> buscarTurmasAtivasComRequisitos();
}