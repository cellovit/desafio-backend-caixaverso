package br.gov.caixa.service;

import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import br.gov.caixa.dto.response.telemetry.TelemetryResponseDto;

import java.time.LocalDate;

public interface TelemetryService {
    TelemetryResponseDto collectTelemetryData();
    TelemetryResponseDto collectTelemetryData(LocalDate dataInicio, LocalDate dataFim);
    void insert(TelemetryMetrics entity);
}
