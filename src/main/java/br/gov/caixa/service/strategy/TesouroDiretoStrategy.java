package br.gov.caixa.service.strategy;

import br.gov.caixa.qualifiers.CalculadoraInvestimentoStrategyQualifier;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@CalculadoraInvestimentoStrategyQualifier("TesouroDireto")
//@RequestScoped
@Slf4j
public class TesouroDiretoStrategy implements CalculadoraInvestimentoStrategy {

    @Override
    public BigDecimal calcularValorFinal(BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses) {
        // Juros simples: VF = VA * (1 + taxa * prazo)
        BigDecimal total = BigDecimal.ONE.add(rentabilidade.multiply(BigDecimal.valueOf(prazoMeses)));
        return valorAplicado.multiply(total);
    }

    @Override
    public BigDecimal calcularRentabilidadeEfetiva(BigDecimal valorAplicado, BigDecimal valorFinal) {
        return valorFinal.subtract(valorAplicado).divide(valorAplicado, 4, BigDecimal.ROUND_HALF_UP);
    }
}
