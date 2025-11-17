package br.gov.caixa.service.context;

import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@ApplicationScoped
@Slf4j
public class SimulacaoInvestimentoContext {

//    private CalculadoraInvestimentoStrategy strategy;
//
//    public InvestimentoContext(CalculadoraInvestimentoStrategy strategy) {
//        this.strategy = strategy;
//    }

    public BigDecimal calcularValorFinal(CalculadoraInvestimentoStrategy strategy, BigDecimal valorAplicado, BigDecimal rentabilidade, int prazoMeses) {
        return strategy.calcularValorFinal(valorAplicado, rentabilidade, prazoMeses);
    }

    public BigDecimal calcularRentabilidadeEfetiva(CalculadoraInvestimentoStrategy strategy, BigDecimal valorAplicado, BigDecimal valorFinal) {
        return strategy.calcularRentabilidadeEfetiva(valorAplicado, valorFinal);
    }

}
