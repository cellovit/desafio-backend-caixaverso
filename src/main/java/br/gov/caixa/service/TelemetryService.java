package br.gov.caixa.service;

import br.gov.caixa.dto.response.telemetry.TelemetryResponseDto;

public interface TelemetryService {
    TelemetryResponseDto collectTelemetryData();
}
