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
class LciStrategyTest {

    @Test
    public void testCalcularValorFinalDelegado() {
        CalculadoraInvestimentoStrategy strategyMock = Mockito.mock(CalculadoraInvestimentoStrategy.class);
        SimulacaoInvestimentoContext context = new SimulacaoInvestimentoContext();

        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal rentabilidade = BigDecimal.valueOf(0.01); // 1% ao mês
        int prazoMeses = 12;
        BigDecimal valorEsperado = BigDecimal.valueOf(1126.83);

        when(strategyMock.calcularValorFinal(valorAplicado, rentabilidade, prazoMeses))
                .thenReturn(valorEsperado);

        BigDecimal resultado = context.calcularValorFinal(strategyMock, valorAplicado, rentabilidade, prazoMeses);

        assertEquals(valorEsperado, resultado);
        verify(strategyMock, times(1)).calcularValorFinal(valorAplicado, rentabilidade, prazoMeses);
    }

    @Test
    public void testCalcularRentabilidadeEfetivaDelegado() {
        CalculadoraInvestimentoStrategy strategyMock = Mockito.mock(CalculadoraInvestimentoStrategy.class);
        SimulacaoInvestimentoContext context = new SimulacaoInvestimentoContext();

        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal valorFinal = BigDecimal.valueOf(1500);
        BigDecimal rentabilidadeEsperada = BigDecimal.valueOf(0.50);

        when(strategyMock.calcularRentabilidadeEfetiva(valorAplicado, valorFinal))
                .thenReturn(rentabilidadeEsperada);

        BigDecimal resultado = context.calcularRentabilidadeEfetiva(strategyMock, valorAplicado, valorFinal);

        assertEquals(rentabilidadeEsperada, resultado);
        verify(strategyMock, times(1)).calcularRentabilidadeEfetiva(valorAplicado, valorFinal);
    }
}

