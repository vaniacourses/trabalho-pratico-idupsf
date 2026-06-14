package com.upsf.backend.repository;

import com.upsf.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByMatricula(String matricula);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByEmailInst(String emailInst);
    boolean existsByNome(String nome);

    Optional<Usuario> findByMatricula(String matricula);
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailInst(String emailInst);

    default Optional<Usuario> findByIdentificador(String identificador) {
        return findByMatricula(identificador)
                .or(() -> findByEmailInst(identificador))
                .or(() -> findByEmail(identificador));
    }
}
