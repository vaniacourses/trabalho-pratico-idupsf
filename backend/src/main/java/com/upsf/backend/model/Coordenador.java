package com.upsf.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class Coordenador extends Docente{
    @OneToOne
    private Curso curso;
    private Date inicioMandato;
    private Date fimMandato;

    public Coordenador(String matricula, String nome, String email, String emailInst, String cpf, String senha,
                       Date dataNasc, Status status, Titulacao titulacao, Regime regime, Departamento departamento,
                       List<String> areasAtuacao, String lattes, Date dataAdmissao, Curso curso, Date inicioMandato, Date fimMandato) {
        super(matricula, nome, email, emailInst, cpf, senha, dataNasc, status, titulacao, regime, departamento, areasAtuacao, lattes, dataAdmissao);
        this.curso = curso;
        this.inicioMandato = inicioMandato;
        this.fimMandato = fimMandato;
    }

}
