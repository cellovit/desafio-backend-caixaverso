package br.gov.caixa.service.strategy.factory;

import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import br.gov.caixa.service.strategy.simulacao.rendafixa.*;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import br.gov.caixa.service.strategy.simulacao.rendavariavel.AcaoStrategy;
import br.gov.caixa.service.strategy.simulacao.rendavariavel.CriptoMoedaStrategy;
import br.gov.caixa.service.strategy.simulacao.rendavariavel.EtfStrategy;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
public class SimulacaoInvestimentoStrategyFactory {

    public static CalculadoraInvestimentoStrategy getStrategy(TipoProdutoInvestimentoEnum tipoProduto) {
        return switch (tipoProduto) {
            case CDB -> new CdbStrategy();
            case LCI, LCA -> new LciStrategy();
            case TESOURO -> new TesouroDiretoStrategy();
            case FUNDO_INVESTIMENTO -> new FundoInvestimentoStrategy();
            case CRI, CRA -> new CriCraStrategy();
            case FII -> new FiiStrategy();
            case DEBENTURE -> new DebentureStrategy();
            case POUPANCA -> new PoupancaStrategy();
            case ETF -> new EtfStrategy();
            case CRIPTOMOEDA -> new CriptoMoedaStrategy();
            case ACAO -> new AcaoStrategy();
        };
    }

}
