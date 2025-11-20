package br.gov.caixa.service.strategy.simulacao.rendafixa;

import br.gov.caixa.qualifiers.CalculadoraInvestimentoStrategyQualifier;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

//@RequestScoped
@Slf4j
@CalculadoraInvestimentoStrategyQualifier("CDB")
public class CdbStrategy implements CalculadoraInvestimentoStrategy {

    @Override
    public BigDecimal calcularValorFinal(BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses) {
        // Juros compostos mensais
        BigDecimal fator = BigDecimal.ONE.add(rentabilidade);
        return valorAplicado.multiply(fator.pow(prazoMeses))
                .setScale(2, RoundingMode.UP);
    }

    @Override
    public BigDecimal calcularRentabilidadeEfetiva(BigDecimal valorAplicado, BigDecimal valorFinal) {
        return valorFinal.subtract(valorAplicado).divide(valorAplicado, 4, BigDecimal.ROUND_HALF_UP)
                .setScale(2, RoundingMode.UP);
    }
}
