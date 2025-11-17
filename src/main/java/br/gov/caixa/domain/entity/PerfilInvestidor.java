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
@Table(name = "perfil_investidor")
public class PerfilInvestidor extends AbstractEntity {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "pontuacao", nullable = false, precision = 3, scale = 2)
    private BigDecimal pontuacao;

    @OneToOne(mappedBy = "perfilInvestidor")
    private Investidor investidor;

    @ManyToMany(mappedBy = "perfis")
    private List<ProdutoInvestimento> produtosRecomendados;

}
