package com.upsf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
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

    public Docente(String matricula, String nome, String email, String emailInst, String cpf, String senha,
                   Date dataNasc, Usuario.Status status, Titulacao titulacao, Regime regime, Departamento departamento,
                   List<String> areasAtuacao, String lattes, LocalDate dataIngresso) {
        super(matricula, nome, email, emailInst, cpf, senha, dataNasc, status, dataIngresso);
        this.titulacao = titulacao;
        this.regime = regime;
        this.departamento = departamento;
        this.areasAtuacao = areasAtuacao;
        this.lattes = lattes;
    }

}
