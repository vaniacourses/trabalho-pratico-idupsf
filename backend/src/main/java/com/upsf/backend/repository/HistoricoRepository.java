package com.upsf.backend.repository;

import com.upsf.backend.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    // talvez seja melhor busca por matricula
    // não é o find by id pois queremos o id do discente e não do historico
    @Query("SELECT h FROM Discente d JOIN d.historico h WHERE d.id = :discenteId")
    Optional<Historico> findByDiscenteId(Long discenteId);

    // Possível Query sem o problema de N+1
    @Query("SELECT d.historico FROM Discente d JOIN FETCH d.historico h JOIN FETCH h.listaDisciplinas dc " +
            "JOIN FETCH dc.turma t JOIN FETCH t.disciplina WHERE d.id = :discenteId")
    Optional<Historico> findHistoricoByDiscenteId(@Param("discenteId") Long discenteId);
}