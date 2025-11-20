package br.gov.caixa.domain.entity.telemetry;

import br.gov.caixa.domain.entity.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "telemetry_metrics")
public class TelemetryMetrics extends AbstractEntity {

    @NotBlank
    @Column(nullable = false)
    String endpoint;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 20, fraction = 2)
    @Column(name = "tempo_resposta", nullable = false, precision = 20, scale = 2)
    BigDecimal tempoResposta;

    @NotNull
    @Column(name = "data_coleta", nullable = false)
    Instant dataColeta;

}
