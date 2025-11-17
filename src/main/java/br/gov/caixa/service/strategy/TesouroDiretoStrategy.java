package br.gov.caixa.service.strategy;

import br.gov.caixa.qualifiers.CalculadoraInvestimentoStrategyQualifier;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@CalculadoraInvestimentoStrategyQualifier("TesouroDireto")
@Slf4j
public class TesouroDiretoStrategy implements CalculadoraInvestimentoStrategy {

    @Override
    public BigDecimal calcularValorFinal(BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses) {
        // Juros simples: VF = VA * (1 + taxa * prazo)
        BigDecimal total = BigDecimal.ONE.add(rentabilidade.multiply(BigDecimal.valueOf(prazoMeses)));
        return valorAplicado.multiply(total).setScale(2, RoundingMode.UP);
    }

    @Override
    public BigDecimal calcularRentabilidadeEfetiva(BigDecimal valorAplicado, BigDecimal valorFinal) {
        return valorFinal.subtract(valorAplicado).divide(valorAplicado, 4, BigDecimal.ROUND_HALF_UP).setScale(2, RoundingMode.UP);
    }
}
