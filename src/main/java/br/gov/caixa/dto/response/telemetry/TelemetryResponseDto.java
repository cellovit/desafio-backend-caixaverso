package br.gov.caixa.dto.response.telemetry;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Builder
public record TelemetryResponseDto(
        List<TelemetryServiceResponseDto> servicos,
        String inicio,
        String fim
) implements Serializable {
}
