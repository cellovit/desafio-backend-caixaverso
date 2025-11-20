package br.gov.caixa.service.strategy.factory;

import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import br.gov.caixa.service.strategy.simulacao.rendafixa.*;
import br.gov.caixa.service.strategy.simulacao.rendavariavel.AcaoStrategy;
import br.gov.caixa.service.strategy.simulacao.rendavariavel.CriptoMoedaStrategy;
import br.gov.caixa.service.strategy.simulacao.rendavariavel.EtfStrategy;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class SimulacaoInvestimentoStrategyFactoryTest {

    @ParameterizedTest
    @EnumSource(TipoProdutoInvestimentoEnum.class)
    public void testGetStrategyReturnsCorrectImplementation(TipoProdutoInvestimentoEnum tipoProduto) {
        CalculadoraInvestimentoStrategy strategy = SimulacaoInvestimentoStrategyFactory.getStrategy(tipoProduto);

        assertNotNull(strategy, "A estratégia não deve ser nula para " + tipoProduto);

        switch (tipoProduto) {
            case CDB -> assertTrue(strategy instanceof CdbStrategy);
            case LCI, LCA -> assertTrue(strategy instanceof LciStrategy);
            case TESOURO -> assertTrue(strategy instanceof TesouroDiretoStrategy);
            case FUNDO_INVESTIMENTO -> assertTrue(strategy instanceof FundoInvestimentoStrategy);
            case CRI, CRA -> assertTrue(strategy instanceof CriCraStrategy);
            case FII -> assertTrue(strategy instanceof FiiStrategy);
            case DEBENTURE -> assertTrue(strategy instanceof DebentureStrategy);
            case POUPANCA -> assertTrue(strategy instanceof PoupancaStrategy);
            case ETF -> assertTrue(strategy instanceof EtfStrategy);
            case CRIPTOMOEDA -> assertTrue(strategy instanceof CriptoMoedaStrategy);
            case ACAO -> assertTrue(strategy instanceof AcaoStrategy);
            default -> fail("Enum não mapeado: " + tipoProduto);
        }
    }
}

