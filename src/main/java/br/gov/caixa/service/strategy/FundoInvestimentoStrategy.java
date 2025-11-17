package br.gov.caixa.service.strategy;

import br.gov.caixa.qualifiers.CalculadoraInvestimentoStrategyQualifier;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@CalculadoraInvestimentoStrategyQualifier("FundoInvestimento")
//@RequestScoped
@Slf4j
public class FundoInvestimentoStrategy implements CalculadoraInvestimentoStrategy {

    private final BigDecimal taxaAdministracaoMensal = new BigDecimal(0.05);

    @Override
    public BigDecimal calcularValorFinal(BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses) {
        // Rentabilidade líquida = rentabilidade - taxa de administração
        BigDecimal rentabilidadeLiquida = rentabilidade.subtract(taxaAdministracaoMensal);
        BigDecimal fator = BigDecimal.ONE.add(rentabilidadeLiquida);
        return valorAplicado.multiply(fator.pow(prazoMeses));
    }

    @Override
    public BigDecimal calcularRentabilidadeEfetiva(BigDecimal valorAplicado, BigDecimal valorFinal) {
        return valorFinal.subtract(valorAplicado).divide(valorAplicado, 4, BigDecimal.ROUND_HALF_UP);
    }
}
