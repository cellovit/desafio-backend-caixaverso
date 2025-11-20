package br.gov.caixa.service.strategy.simulacao.rendavariavel;

import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class CriptoMoedaStrategy implements CalculadoraInvestimentoStrategy {
    @Override
    public BigDecimal calcularValorFinal(BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses) {
        return valorAplicado.multiply(
                BigDecimal.valueOf(Math.pow(1 + rentabilidade.doubleValue(), prazoMeses))
        ).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcularRentabilidadeEfetiva(BigDecimal valorAplicado, BigDecimal valorFinal) {
        return valorFinal.subtract(valorAplicado)
                .divide(valorAplicado, 4, RoundingMode.HALF_UP);
    }
}

