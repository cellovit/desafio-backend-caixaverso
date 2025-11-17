package br.gov.caixa.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Investidor extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(name = "idade", nullable = false)
    private Integer idade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id", nullable = false)
    private PerfilInvestidor perfilInvestidor;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investimento> investimentos;

}
