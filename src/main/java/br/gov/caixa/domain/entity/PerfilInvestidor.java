package br.gov.caixa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PerfilInvestidor extends AbstractEntity {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "pontuacao", nullable = false, precision = 3, scale = 2)
    private Double pontuacao;

    @OneToOne(mappedBy = "perfilInvestidor")
    private Investidor investidor;

    @ManyToMany(mappedBy = "perfis")
    private List<ProdutoInvestimento> produtosRecomendados;

}
