package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.Simulacao;
import br.gov.caixa.dto.PageParams;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.panache.common.Page;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class SimulacaoDaoTest {

    @Test
    public void testFindPaginado() {
        PageParams pageParams = new PageParams(1, 5);

        Simulacao simulacao = new Simulacao();
        simulacao.setProduto("CDB Banco XP");
        simulacao.setValorFinal(BigDecimal.valueOf(1200));
        simulacao.setDataSimulacao(LocalDate.of(2025, 1, 1));

        PanacheQuery<Simulacao> queryMock = Mockito.mock(PanacheQuery.class);

        when(queryMock.page(any(Page.class))).thenReturn(queryMock);
        when(queryMock.list()).thenReturn(List.of(simulacao));

        SimulacaoDao daoSpy = Mockito.spy(new SimulacaoDao());
        doReturn(queryMock).when(daoSpy).findAll();

        List<Simulacao> result = daoSpy.findPaginado(pageParams);

        assertEquals(1, result.size());
        assertEquals("CDB Banco XP", result.get(0).getProduto());
        assertEquals(BigDecimal.valueOf(1200), result.get(0).getValorFinal());

        verify(queryMock, times(1)).page(any(Page.class));
        verify(queryMock, times(1)).list();
    }
}

