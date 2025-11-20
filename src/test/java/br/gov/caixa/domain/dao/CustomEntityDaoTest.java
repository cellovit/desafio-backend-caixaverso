package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.repository.CustomEntityRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaQueryResultDto;
import br.gov.caixa.dto.response.telemetry.TelemetryServiceResponseDto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
@ExtendWith(MockitoExtension.class)
public class CustomEntityDaoTest {

    @InjectMock
    CustomEntityDao customEntityDao;

    @InjectMock
    CustomEntityRepository customEntityRepository;

    @Mock
    PanacheQuery<SimulacaoProdutoDiaQueryResultDto> simulacaoQuery;

    @Test
    public void testSimulacaoPorProdutoDia() {
        PageParams pageParams = new PageParams(1, 10);
        SimulacaoProdutoDiaQueryResultDto dto =
                new SimulacaoProdutoDiaQueryResultDto("CDB", 5L, LocalDate.of(2025,1,1), 1200.0);

        when(customEntityDao.simulacaoPorProdutoDia(any())).thenReturn(List.of(dto));

        List<SimulacaoProdutoDiaQueryResultDto> result = customEntityDao.simulacaoPorProdutoDia(pageParams);

        assertEquals(1, result.size());
        assertEquals("CDB", result.get(0).getProduto());
    }

    @Test
    public void testFiltrarDadosTelemetriaPorPeriodo() {
        LocalDate inicio = LocalDate.of(2025, 1, 1);
        LocalDate fim = LocalDate.of(2025, 1, 31);

        TelemetryServiceResponseDto dto =
                new TelemetryServiceResponseDto("endpointA", 10L, 123.45);
        when(customEntityDao.filtrarDadosTelemetriaPorPeriodo(any(), any())).thenReturn(List.of(dto));

        List<TelemetryServiceResponseDto> result = customEntityDao.filtrarDadosTelemetriaPorPeriodo(inicio, fim);

        assertEquals(1, result.size());
        assertEquals("endpointA", result.get(0).getNome());
        assertEquals(10L, result.get(0).getQuantidadeChamadas());
    }
}

