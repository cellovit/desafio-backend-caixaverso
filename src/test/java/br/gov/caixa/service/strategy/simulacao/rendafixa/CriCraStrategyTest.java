package br.gov.caixa.service.strategy.simulacao.rendafixa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class CriCraStrategyTest {

    @Test
    public void testCalcularValorFinal() {
        CriCraStrategy strategy = new CriCraStrategy();
        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal rentabilidade = BigDecimal.valueOf(0.01);
        int prazoMeses = 12;

        BigDecimal resultado = strategy.calcularValorFinal(valorAplicado, rentabilidade, prazoMeses);

        assertEquals(BigDecimal.valueOf(1126.83).setScale(2, RoundingMode.HALF_UP), resultado);
    }

    @Test
    public void testCalcularRentabilidadeEfetiva() {
        CriCraStrategy strategy = new CriCraStrategy();
        BigDecimal valorAplicado = BigDecimal.valueOf(1000);
        BigDecimal valorFinal = BigDecimal.valueOf(1500);

        BigDecimal resultado = strategy.calcularRentabilidadeEfetiva(valorAplicado, valorFinal);

        assertEquals(BigDecimal.valueOf(0.50).setScale(4, RoundingMode.HALF_UP), resultado);
    }
}

