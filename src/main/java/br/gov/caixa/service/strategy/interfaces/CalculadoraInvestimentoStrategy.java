package br.gov.caixa.service.strategy.interfaces;

import java.math.BigDecimal;

public interface CalculadoraInvestimentoStrategy {
    BigDecimal calcularValorFinal(BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses);
    BigDecimal calcularRentabilidadeEfetiva(BigDecimal valorAplicado, BigDecimal valorFinal);
}
