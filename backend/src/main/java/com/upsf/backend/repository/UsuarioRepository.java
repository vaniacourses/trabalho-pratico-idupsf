package com.upsf.backend.repository;

import com.upsf.backend.model.Usuario;

public class UsuarioRepository {
    boolean existsByMatricula(String matricula);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByEmailInst(String emailInst);
    boolean existsByNome(String nome);
}