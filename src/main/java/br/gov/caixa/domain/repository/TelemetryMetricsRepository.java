package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.time.LocalDate;
import java.util.List;

public interface TelemetryMetricsRepository extends PanacheRepository<TelemetryMetrics> {
    List<TelemetryMetrics> filtrarDadosTelemetriaPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
}
