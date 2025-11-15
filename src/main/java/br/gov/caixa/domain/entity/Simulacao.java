package br.gov.caixa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Simulacao extends AbstractEntity {

    @NotBlank
    @Column(nullable = false)
    private String produto;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 20, fraction = 2)
    @Column(name = "valor_investido", nullable = false, precision = 20, scale = 2)
    private BigDecimal valorInvestido;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 20, fraction = 2)
    @Column(name = "valor_final", nullable = false, precision = 20, scale = 2)
    private BigDecimal valorFinal;

    @NotNull
    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @NotNull
    @Column(name = "data_simulacao", nullable = false)
    private Instant dataSimulacao;
}
