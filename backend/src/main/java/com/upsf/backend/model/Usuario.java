package com.upsf.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricula;
    private String nome;
    private String email;
    private String emailInst;
    private String cpf;
    private String senha;
    private Date dataNasc;
    private LocalDate dataIngresso;
    public enum Status{ATIVO, INATIVO};
    @Enumerated(EnumType.STRING)
    private Status status;

    public Usuario(String matricula, String nome, String email, String emailInst,
                   String cpf, String senha, Date dataNasc, Status status, LocalDate dataIngresso) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.emailInst = emailInst;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.status = status;
        this.dataIngresso = dataIngresso;
    }

}
