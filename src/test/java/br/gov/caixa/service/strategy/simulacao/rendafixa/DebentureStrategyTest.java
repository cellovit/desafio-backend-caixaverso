package br.gov.caixa.service.strategy.simulacao.rendafixa;


import br.gov.caixa.service.context.SimulacaoInvestimentoContext;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class DebentureStrategyTest {

    @Test
    public void testCalcularValorFinalDelegado() {
        SimulacaoInvestimentoContext context = new SimulacaoInvestimentoContext();
        DebentureStrategy debentureStrategy = new DebentureStrategy();

        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal rentabilidade = BigDecimal.valueOf(0.02);
        int prazoMeses = 12;
        BigDecimal valorEsperado = BigDecimal.valueOf(1268.24);

        BigDecimal resultado = context.calcularValorFinal(debentureStrategy, valorAplicado, rentabilidade, prazoMeses);

        assertEquals(valorEsperado, resultado);
    }

    @Test
    public void testCalcularRentabilidadeEfetivaDelegado() {
        DebentureStrategy debentureStrategy = new DebentureStrategy();
        SimulacaoInvestimentoContext context = new SimulacaoInvestimentoContext();

        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal valorFinal = BigDecimal.valueOf(1500);
        BigDecimal rentabilidadeEsperada = BigDecimal.valueOf(0.50);

        BigDecimal resultado = debentureStrategy.calcularRentabilidadeEfetiva(valorAplicado, valorFinal);

        assertEquals(rentabilidadeEsperada, resultado.setScale(1));
    }
}

