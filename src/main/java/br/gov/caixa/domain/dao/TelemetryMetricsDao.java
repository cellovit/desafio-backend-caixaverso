package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import br.gov.caixa.domain.repository.TelemetryMetricsRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@ApplicationScoped
public class TelemetryMetricsDao implements TelemetryMetricsRepository {

    @Override
    public List<TelemetryMetrics> filtrarDadosTelemetriaPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        Instant inicio = dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant fim = dataFim.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return find("SELECT t FROM TelemetryMetrics t " +
                        "WHERE t.dataColeta BETWEEN :dataInicio AND :dataFim",
                Parameters.with("dataInicio", inicio)
                        .and("dataFim", fim))
                .list();
    }

}
