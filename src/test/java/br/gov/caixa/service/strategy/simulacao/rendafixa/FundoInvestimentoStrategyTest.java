package br.gov.caixa.service.strategy.simulacao.rendafixa;

import br.gov.caixa.service.context.SimulacaoInvestimentoContext;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class FundoInvestimentoStrategyTest {

    @Test
    public void testCalcularValorFinalDelegado() {
        FundoInvestimentoStrategy fundoInvestimentoStrategy = new FundoInvestimentoStrategy();

        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal rentabilidade = BigDecimal.valueOf(0.10);
        int prazoMeses = 6;
        BigDecimal valorEsperado = BigDecimal.valueOf(1340.1);

        BigDecimal resultado = fundoInvestimentoStrategy.calcularValorFinal(valorAplicado, rentabilidade, prazoMeses);

        assertEquals(valorEsperado, resultado.setScale(1));
    }

    @Test
    public void testCalcularRentabilidadeEfetivaDelegado() {
        FundoInvestimentoStrategy fundoInvestimentoStrategy = new FundoInvestimentoStrategy();

        BigDecimal valorAplicado = BigDecimal.valueOf(2000);
        BigDecimal valorFinal = BigDecimal.valueOf(2600);
        BigDecimal rentabilidadeEsperada = BigDecimal.valueOf(0.30);

        BigDecimal resultado = fundoInvestimentoStrategy.calcularRentabilidadeEfetiva(valorAplicado, valorFinal);

        assertEquals(rentabilidadeEsperada, resultado.setScale(1));
    }
}

