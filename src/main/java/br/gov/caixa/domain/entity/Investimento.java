package br.gov.caixa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Investimento extends AbstractEntity {

    @NotBlank
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "valor_aplicado", nullable = false, precision = 20, scale = 2)
    private BigDecimal valorAplicado;

    @Column(name = "prazo_meses", nullable = true)
    private Integer prazoMeses;

    @Column(name = "data_aplicacao", nullable = false)
    private LocalDate dataAplicacao;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "rentabilidade", nullable = false, precision = 10, scale = 4)
    private BigDecimal rentabilidade;

    @ManyToOne
    @JoinColumn(name = "investidor_id", nullable = false)
    private Investidor investidor;
}
