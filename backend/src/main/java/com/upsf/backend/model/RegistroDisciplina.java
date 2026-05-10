package com.upsf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class RegistroDisciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public enum TipoCategoria{OBRIGATORIA, ELETIVA, OPTATIVA, AVULSA};
    @Enumerated(EnumType.STRING)
    private TipoCategoria tipoCategoria;
    private int periodoRecomendado;
    @ManyToOne
    private Disciplina disciplina;

    public RegistroDisciplina(TipoCategoria tipoCategoria, int periodoRecomendado, Disciplina disciplina) {
        this.tipoCategoria = tipoCategoria;
        this.periodoRecomendado = periodoRecomendado;
        this.disciplina = disciplina;
    }

}
