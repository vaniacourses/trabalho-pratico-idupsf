package com.upsf.backend.service;

import com.upsf.backend.dto.LoginRequestDTO;
import com.upsf.backend.dto.LoginResponseDTO;
import com.upsf.backend.model.Coordenador;
import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;
import com.upsf.backend.security.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.cpf(),
                        loginRequestDTO.senha()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        Usuario usuario = usuarioDetails.getUsuario();

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getMatricula(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getEmailInst(),
                usuario.getStatus(),
                getTipo(usuario)
        );
    }

    private String getTipo(Usuario usuario) {
        if (usuario instanceof Coordenador) {
            return "COORDENADOR";
        }
        if (usuario instanceof Discente) {
            return "DISCENTE";
        }
        if (usuario instanceof Docente) {
            return "DOCENTE";
        }
        throw new IllegalStateException("Tipo de usuário não suportado.");
    }
}
