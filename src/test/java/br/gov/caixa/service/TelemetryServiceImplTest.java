package br.gov.caixa.service;

import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import br.gov.caixa.domain.repository.CustomEntityRepository;
import br.gov.caixa.dto.response.telemetry.TelemetryResponseDto;
import br.gov.caixa.dto.response.telemetry.TelemetryServiceResponseDto;
import br.gov.caixa.service.impl.TelemetryServiceImpl;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class TelemetryServiceImplTest {

    @InjectMock
    CustomEntityRepository repository;

    @Inject
    TelemetryServiceImpl telemetryService;

    @Test
    public void testCollectTelemetryData() {
        // Arrange
        LocalDate inicio = LocalDate.of(2025, 1, 1);
        LocalDate fim = LocalDate.of(2025, 1, 31);

        var dto = TelemetryServiceResponseDto.builder()
                .mediaTempoRespostaMs(70.0)
                .nome("/teste")
                .quantidadeChamadas(2L)
                .build();

        List<TelemetryServiceResponseDto> dadosMock = List.of(dto);
        when(repository.filtrarDadosTelemetriaPorPeriodo(inicio, fim)).thenReturn(dadosMock);

        TelemetryResponseDto response = telemetryService.collectTelemetryData(inicio, fim);

        assertEquals("2025-01-01", response.inicio());
        assertEquals("2025-01-31", response.fim());
        assertEquals(1, response.servicos().size());
        assertEquals("/teste", response.servicos().get(0).getNome() );

        verify(repository, times(1)).filtrarDadosTelemetriaPorPeriodo(inicio, fim);
    }

    @Test
    public void testInsert() {
        TelemetryMetrics metrics = new TelemetryMetrics();
        metrics.setEndpoint("/api/test");
        metrics.setTempoResposta(java.math.BigDecimal.valueOf(123.45));
        metrics.setDataColeta(java.time.Instant.now());

        telemetryService.insert(metrics);

        verify(repository, times(1)).persist(metrics);
    }
}

