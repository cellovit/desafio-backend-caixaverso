package br.gov.caixa.service.strategy.factory;

import br.gov.caixa.service.strategy.CdbStrategy;
import br.gov.caixa.service.strategy.FundoInvestimentoStrategy;
import br.gov.caixa.service.strategy.LciStrategy;
import br.gov.caixa.service.strategy.TesouroDiretoStrategy;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
public class SimulacaoInvestimentoStrategyFactory {

    public static CalculadoraInvestimentoStrategy getStrategy(String tipoProduto) {
        return switch (tipoProduto.toUpperCase()) {
            case "CDB" -> new CdbStrategy();
            case "LCI", "LCA" -> new LciStrategy();
            case "TESOURO" -> new TesouroDiretoStrategy();
            case "FUNDO" -> new FundoInvestimentoStrategy();
            default -> throw new IllegalArgumentException("Tipo de produto não suportado: " + tipoProduto);
        };
    }

}
