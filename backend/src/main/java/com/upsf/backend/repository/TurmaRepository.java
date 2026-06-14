package com.upsf.backend.repository;

import com.upsf.backend.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long>, JpaSpecificationExecutor<Turma> {

    // Acho que esta consulta é desnecessária (!!! - Cláudio)
    // Consulta das Turmas Ativas com Disciplina e Horário
    @Query("SELECT t FROM Turma t LEFT JOIN FETCH t.disciplina " +
            "LEFT JOIN FETCH t.horario LEFT JOIN FETCH t.docente WHERE t.status = 'ATIVA' order by t.id")
    List<Turma> buscarTurmasAtivas();

    @Query("SELECT t FROM Turma t LEFT JOIN FETCH t.disciplina " +
            "LEFT JOIN FETCH t.horario LEFT JOIN FETCH t.docente WHERE t.id = :id")
    Optional<Turma> buscarTurmaPorId(@Param("id") Long id);


    // Consulta das Turmas Ativas com Pré-Requisitos (Para InscricaoService)
    @Query("SELECT t FROM Turma t JOIN FETCH t.disciplina d " +
            "LEFT JOIN FETCH d.preRequisitos WHERE t.status = 'ATIVA'")
    List<Turma> buscarTurmasAtivasComRequisitos();

    @Query("SELECT t FROM Turma t WHERE t.disciplina.id IN (SELECT rd.disciplina.id FROM Curso c JOIN c.curriculos cur JOIN cur.registroDisciplinas rd WHERE c.id = :cursoId)")
    List<Turma> findTurmasByCurriculosDoCurso(@Param("cursoId") Long cursoId);

}