package br.gov.caixa.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "investidor")
public class Investidor extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    private PerfilInvestidor perfilInvestidor;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investimento> investimentos;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Simulacao> simulacoes;

}
