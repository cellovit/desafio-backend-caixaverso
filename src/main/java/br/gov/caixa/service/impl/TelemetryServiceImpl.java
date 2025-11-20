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
    CustomEntityRepository repository;

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
        repository.persist(entity);
    }

}
