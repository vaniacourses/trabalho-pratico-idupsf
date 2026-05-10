package com.upsf.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Docente extends Usuario{
    public enum Titulacao {MESTRE, DOUTOR}; // ou phd, msc
    public enum Regime{DE, TP};
    @Enumerated(EnumType.STRING)
    private Titulacao titulacao;
    @Enumerated(EnumType.STRING)
    private Regime regime;
    @ManyToOne
    private Departamento departamento;
    @ElementCollection
    private List<String> areasAtuacao;
    private String lattes;
    private Date dataAdmissao;

    public Docente(String matricula, String nome, String email, String emailInst, String cpf, String senha,
                   Date dataNasc, Usuario.Status status, Titulacao titulacao, Regime regime, Departamento departamento,
                   List<String> areasAtuacao, String lattes, Date dataAdmissao) {
        super(matricula, nome, email, emailInst, cpf, senha, dataNasc, status);
        this.titulacao = titulacao;
        this.regime = regime;
        this.departamento = departamento;
        this.areasAtuacao = areasAtuacao;
        this.lattes = lattes;
        this.dataAdmissao = dataAdmissao;
    }

    public void adicionarAreaAtuacao(String novaArea) {
        this.areasAtuacao.add(novaArea);
    }

    public void removerAreaAtuacao(String area) {
        this.areasAtuacao.remove(area);
    }

    public void atualizarSenha(String novaSenha){
        setSenha(novaSenha);
    }
    public void desativarConta(){}
}
