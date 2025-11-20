package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class TelemetryMetricsDaoTest {

    @Test
    public void testFiltrarDadosTelemetriaPorPeriodo() {
        // Arrange
        LocalDate inicio = LocalDate.of(2025, 1, 1);
        LocalDate fim = LocalDate.of(2025, 1, 31);

        TelemetryMetrics metric = new TelemetryMetrics();
        metric.setEndpoint("/api/test");
        metric.setTempoResposta(BigDecimal.valueOf(123.45));
        metric.setDataColeta(Instant.now());

        PanacheQuery<TelemetryMetrics> queryMock = Mockito.mock(PanacheQuery.class);
        when(queryMock.list()).thenReturn(List.of(metric));

        // Espiona o DAO para interceptar find(...)
        TelemetryMetricsDao daoSpy = Mockito.spy(new TelemetryMetricsDao());
        doReturn(queryMock).when(daoSpy).find(
                anyString(),
                any(Parameters.class)
        );

        // Act
        List<TelemetryMetrics> result = daoSpy.filtrarDadosTelemetriaPorPeriodo(inicio, fim);

        // Assert
        assertEquals(1, result.size());
        assertEquals("/api/test", result.get(0).getEndpoint());
        assertEquals(BigDecimal.valueOf(123.45), result.get(0).getTempoResposta());

        verify(queryMock, times(1)).list();
    }
}

