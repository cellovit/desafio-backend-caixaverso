package br.gov.caixa.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "simulacao")
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
    private LocalDate dataSimulacao;

    @ManyToOne
    @JoinColumn(name = "investidor_id", nullable = false)
    private Investidor investidor;
}
