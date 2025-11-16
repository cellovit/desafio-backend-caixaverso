package br.gov.caixa.dto.response.telemetry;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record TelemetryServiceResponseDto(
        String nome,
        Long quantidadeChamadas,
        Double mediaTempoRespostaMs
) implements Serializable {
}
