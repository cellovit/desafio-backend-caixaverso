package br.gov.caixa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Produto extends AbstractEntity {

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 3, fraction = 4)
    @Column(name = "rentabilidade", nullable = false, precision = 10, scale = 4)
    private BigDecimal rentabilidade;

    @NotBlank
    @Column(name = "risco", nullable = false)
    private String risco;

}
