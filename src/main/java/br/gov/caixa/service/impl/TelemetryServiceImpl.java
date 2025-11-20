package br.gov.caixa.service.impl;

import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import br.gov.caixa.domain.repository.CustomEntityRepository;
import br.gov.caixa.domain.repository.TelemetryMetricsRepository;
import br.gov.caixa.dto.response.telemetry.TelemetryResponseDto;
import br.gov.caixa.dto.response.telemetry.TelemetryServiceResponseDto;
import br.gov.caixa.service.TelemetryService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.MeterNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
@Slf4j
public class TelemetryServiceImpl implements TelemetryService {

    @Inject
    MeterRegistry registry;

    @Inject
    CustomEntityRepository repository;

    @Inject
    TelemetryMetricsRepository telemetryMetricsRepository;

    @Override
    // TODO: corrigir
    public TelemetryResponseDto collectTelemetryData() {
        var servicos = new ArrayList<TelemetryServiceResponseDto>();
        try {
            final String SERVER_REQUESTS = "http.server.requests";

            registry.get(SERVER_REQUESTS).timers()
                    .stream()
//                .filter(x -> x.getId().getTag("outcome") == "SUCCESS")
                    // .filter(x -> StringUtils.contains(x.getId().getTag("uri"), "/"))
                    .forEach(t -> {
                        var servico = TelemetryServiceResponseDto.builder()
                                .nome(t.getId().getTag("uri"))
                                .mediaTempoRespostaMs(t.mean(TimeUnit.MILLISECONDS))
                                .quantidadeChamadas(t.count())
                                .build();
                        servicos.add(servico);
                    });

            var response = TelemetryResponseDto.builder()
                    .servicos(servicos)
                    .inicio(LocalDate.now().toString())
                    .fim(LocalDate.now().toString())
                    .build();

            return response;
        } catch (MeterNotFoundException ex) {
            return TelemetryResponseDto.builder()
                    .servicos(servicos)
                    .inicio(LocalDate.now().toString())
                    .fim(LocalDate.now().toString())
                    .build();
        }
    }

    @Override
    public TelemetryResponseDto collectTelemetryData(LocalDate dataInicio, LocalDate dataFim) {
        return TelemetryResponseDto.builder()
                .servicos(repository.filtrarDadosTelemetriaPorPeriodo(dataInicio, dataFim))
                .inicio(dataInicio.toString())
                .fim(dataFim.toString())
                .build();
    }

    @Override
    @Transactional
    public void insert(TelemetryMetrics entity) {

        var all = telemetryMetricsRepository.listAll();
        log.info("TELE1: {}", all);
        repository.persist(entity);
    }

}
