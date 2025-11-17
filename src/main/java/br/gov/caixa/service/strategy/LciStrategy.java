package br.gov.caixa.service.strategy;

import br.gov.caixa.qualifiers.CalculadoraInvestimentoStrategyQualifier;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@CalculadoraInvestimentoStrategyQualifier("Lci")
//@RequestScoped
@Slf4j
public class LciStrategy implements CalculadoraInvestimentoStrategy {

    @Override
    public BigDecimal calcularValorFinal(BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses) {
        // Juros compostos mensais, sem IR
        BigDecimal fator = BigDecimal.ONE.add(rentabilidade);
        return valorAplicado.multiply(fator.pow(prazoMeses));
    }

    @Override
    public BigDecimal calcularRentabilidadeEfetiva(BigDecimal valorAplicado, BigDecimal valorFinal) {
        return valorFinal.subtract(valorAplicado).divide(valorAplicado, 4, BigDecimal.ROUND_HALF_UP);
    }

}
