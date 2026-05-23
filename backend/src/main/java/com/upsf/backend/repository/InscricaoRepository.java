package com.upsf.backend.repository;

import com.upsf.backend.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByTurmaId(Long turmaId);

    @Transactional
    void deleteByTurmaId(Long turmaId);
}
