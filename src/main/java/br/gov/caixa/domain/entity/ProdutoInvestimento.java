package br.gov.caixa.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ProdutoInvestimento extends AbstractEntity {

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome; // ex.: "CDB Banco X", "Tesouro Selic"

    @NotNull
    @Column(name = "tipo", nullable = false)
    private String tipo; // renda fixa, renda variável, fundo, etc.

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "rentabilidade", nullable = false, precision = 10, scale = 4)
    private BigDecimal rentabilidade;

    @NotNull
    @Column(name = "risco", nullable = false)
    private String risco; // baixo, médio, alto

    @ManyToMany
    @JoinTable(
            name = "produto_perfil",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<PerfilInvestidor> perfis;

}
