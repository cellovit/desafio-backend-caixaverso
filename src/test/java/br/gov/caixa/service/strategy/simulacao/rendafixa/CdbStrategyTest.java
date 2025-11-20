package br.gov.caixa.service.strategy.simulacao.rendafixa;

import br.gov.caixa.service.context.SimulacaoInvestimentoContext;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
class CdbStrategyTest {

    @Test
    public void testDelegacaoParaCdbStrategy() {
        CalculadoraInvestimentoStrategy strategyMock = Mockito.mock(CalculadoraInvestimentoStrategy.class);
        SimulacaoInvestimentoContext context = new SimulacaoInvestimentoContext();

        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal rentabilidade = BigDecimal.valueOf(0.01);
        int prazoMeses = 12;
        BigDecimal valorEsperado = BigDecimal.valueOf(1126.83);

        when(strategyMock.calcularValorFinal(valorAplicado, rentabilidade, prazoMeses))
                .thenReturn(valorEsperado);

        BigDecimal resultado = context.calcularValorFinal(strategyMock, valorAplicado, rentabilidade, prazoMeses);

        assertEquals(valorEsperado, resultado);
        verify(strategyMock, times(1)).calcularValorFinal(valorAplicado, rentabilidade, prazoMeses);
    }

}
