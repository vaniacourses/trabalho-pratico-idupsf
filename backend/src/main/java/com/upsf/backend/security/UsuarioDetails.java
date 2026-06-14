package com.upsf.backend.security;

import com.upsf.backend.model.Coordenador;
import com.upsf.backend.model.Discente;
import com.upsf.backend.model.Docente;
import com.upsf.backend.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsuarioDetails implements UserDetails {
    private final Usuario usuario;

    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (usuario instanceof Coordenador) {
            return List.of(new SimpleGrantedAuthority("COORDENADOR"));
        }
        if (usuario instanceof Discente) {
            return List.of(new SimpleGrantedAuthority("DISCENTE"));
        }
        if (usuario instanceof Docente) {
            return List.of(new SimpleGrantedAuthority("DOCENTE"));
        }
        return List.of();
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getCpf();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario.getStatus() == Usuario.Status.ATIVO;
    }
}
