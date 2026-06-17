package com.upsf.backend.repository;

import com.upsf.backend.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    // nao sei se precisa desse
    Optional<Disciplina> findByNomeIgnoreCase(String nome);

    List<Disciplina> findByNomeContainingIgnoreCase(String nome);

    List<Disciplina> findByDepartamentoId(Long departamentoId);
}
