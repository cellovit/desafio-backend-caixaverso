package br.gov.caixa.service.impl;

import br.gov.caixa.dto.response.telemetry.TelemetryResponseDto;
import br.gov.caixa.dto.response.telemetry.TelemetryServiceResponseDto;
import br.gov.caixa.service.TelemetryService;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
@Slf4j
public class TelemetryServiceImpl implements TelemetryService {

    @Inject
    MeterRegistry registry;

    @Override
    public TelemetryResponseDto collectTelemetryData() {
        var servicos = new ArrayList<TelemetryServiceResponseDto>();

        registry.get("http.server.requests").timers()
                .forEach(t -> {
                    var servico = TelemetryServiceResponseDto.builder()
                            .nome(t.getId().getTag("uri"))
                            .mediaTempoRespostaMs(t.mean(TimeUnit.MILLISECONDS))
                            .quantidadeChamadas(t.count())
                            .build();
                    servicos.add(servico);
                });

        String inicio = "2025-10-01";
        String fim = "2025-10-31";

        var response = TelemetryResponseDto.builder()
                .servicos(servicos)
                .inicio(inicio)
                .fim(fim)
                .build();

        return response;
    }

}
