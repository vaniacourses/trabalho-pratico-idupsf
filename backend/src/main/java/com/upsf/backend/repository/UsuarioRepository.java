package com.upsf.backend.repository;

import com.upsf.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByMatricula(String matricula);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByEmailInst(String emailInst);
    boolean existsByNome(String nome);
}