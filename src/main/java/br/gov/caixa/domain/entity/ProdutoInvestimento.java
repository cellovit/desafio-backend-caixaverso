package br.gov.caixa.domain.entity;

import br.gov.caixa.domain.enums.RiscoInvestimentoEnum;
import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
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
@Table(name = "produto_investimento")
public class ProdutoInvestimento extends AbstractEntity {

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProdutoInvestimentoEnum tipo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "rentabilidade", nullable = false, precision = 10, scale = 4)
    private BigDecimal rentabilidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "risco", nullable = false)
    private RiscoInvestimentoEnum risco;

//    @ManyToMany
//    @JoinTable(
//            name = "produto_perfil",
//            joinColumns = @JoinColumn(name = "produto_id"),
//            inverseJoinColumns = @JoinColumn(name = "perfil_id")
//    )
//    private List<PerfilInvestidor> perfis;

}
