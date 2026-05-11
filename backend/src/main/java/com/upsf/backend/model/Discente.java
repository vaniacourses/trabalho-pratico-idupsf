package com.upsf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Discente extends Usuario{
    @ManyToOne
    private Curso curso;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Historico historico;
    private String periodo;
    private String periodoIngresso;
    private String codCurriculo;
    public enum SituacaoAcademica{ATIVO, FORMADO, JUBILADO, TRANCADO}
    public enum FormaPermanencia{DEFINITIVA, TEMPORARIA}
    @Enumerated(EnumType.STRING)
    private SituacaoAcademica situacaoAcademica;
    @Enumerated(EnumType.STRING)
    private FormaPermanencia formaPermanencia;

    public Discente(String matricula, String nome, String email, String emailInst, String cpf, String senha,
                    Date dataNasc, Status status, Curso curso, Historico historico, String periodo, String periodoIngresso,
                    String codCurriculo, SituacaoAcademica situacaoAcademica, FormaPermanencia formaPermanencia) {
        super(matricula, nome, email, emailInst, cpf, senha, dataNasc, status);
        this.curso = curso;
        this.historico = historico;
        this.periodo = periodo;
        this.periodoIngresso = periodoIngresso;
        this.codCurriculo = codCurriculo;
        this.situacaoAcademica = situacaoAcademica;
        this.formaPermanencia = formaPermanencia;
    }

    void atualizarSenha(String novaSenha){}
    void desativarConta(){}
    void trancarMatricula(){}
    boolean estarRegular(){return true;}
}
