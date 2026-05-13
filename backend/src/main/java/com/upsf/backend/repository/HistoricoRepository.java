package com.upsf.backend.repository;

import com.upsf.backend.model.Disciplina;
import com.upsf.backend.model.DisciplinaCursada;
import com.upsf.backend.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    // talvez seja melhor busca por matricula
    // não é o find by id pois queremos o id do discente e não do historico
    @Query("SELECT h FROM Discente d JOIN d.historico h WHERE d.id = :discenteId")
    Optional<Historico> findByDiscenteId(Long discenteId);

    // Possível Query sem o problema de N+1
    @Query("SELECT h FROM Historico h " +
            "JOIN FETCH h.listaDisciplinas dc " +
            "JOIN FETCH dc.turma t " +
            "JOIN FETCH t.disciplina " +
            "WHERE h.discente.id = :discenteId")
    Optional<Historico> findHistoricoByDiscenteId(@Param("discenteId") Long discenteId);

    @Query("SELECT DISTINCT t.disciplina FROM Historico h " +
            "JOIN h.listaDisciplinas dc " +
            "JOIN dc.turma t " +
            "WHERE h.discente.id = :discenteId " +
            "AND dc.statusFinal = :status")
    List<Disciplina> findDisciplinasByDiscenteAndStatus(
            @Param("discenteId") Long discenteId,
            @Param("status") DisciplinaCursada.StatusFinal status
    );


}