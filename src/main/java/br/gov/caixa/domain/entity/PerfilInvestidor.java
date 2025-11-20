package br.gov.caixa.domain.entity;

import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil_investidor")
public class PerfilInvestidor extends AbstractEntity {

    @Column(name = "titulo")
    @Enumerated(EnumType.STRING)
    private PerfilInvestidorEnum titulo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "pontuacao", nullable = false, precision = 5, scale = 2)
    private BigDecimal pontuacao;

    @OneToOne(mappedBy = "perfilInvestidor")
    private Investidor investidor;
//
//    @ManyToMany(mappedBy = "perfis")
//    private List<ProdutoInvestimento> produtosRecomendados;

}
