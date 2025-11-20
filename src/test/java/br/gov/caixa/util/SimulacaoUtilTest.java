package br.gov.caixa.util;

import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.enums.RiscoInvestimentoEnum;
import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
class SimulacaoUtilTest {

    @Test
    public void testDefineRiscoPorPerfil() {
        assertEquals(List.of(RiscoInvestimentoEnum.BAIXO),
                SimulacaoUtil.defineRiscoPorPerfil(PerfilInvestidorEnum.CONSERVADOR));

        assertEquals(List.of(RiscoInvestimentoEnum.MEDIO),
                SimulacaoUtil.defineRiscoPorPerfil(PerfilInvestidorEnum.MODERADO));

        assertEquals(List.of(RiscoInvestimentoEnum.ALTO, RiscoInvestimentoEnum.MUITO_ALTO),
                SimulacaoUtil.defineRiscoPorPerfil(PerfilInvestidorEnum.AGRESSIVO));
    }

    @Test
    public void testCalcularFocoComMock() {
        Investimento investimento1 = Mockito.mock(Investimento.class);
        Investimento investimento2 = Mockito.mock(Investimento.class);

        TipoProdutoInvestimentoEnum tipo1 = Mockito.mock(TipoProdutoInvestimentoEnum.class);
        TipoProdutoInvestimentoEnum tipo2 = Mockito.mock(TipoProdutoInvestimentoEnum.class);

        when(investimento1.getTipo()).thenReturn(tipo1);
        when(investimento2.getTipo()).thenReturn(tipo2);

        when(tipo1.getFoco()).thenReturn(FocoProdutoInvestimentoEnum.RENTABILIDADE);
        when(tipo2.getFoco()).thenReturn(FocoProdutoInvestimentoEnum.RENTABILIDADE);

        FocoProdutoInvestimentoEnum foco = SimulacaoUtil.calculaFoco(List.of(investimento1, investimento2));

        assertEquals(FocoProdutoInvestimentoEnum.RENTABILIDADE, foco);
        verify(tipo1, times(1)).getFoco();
        verify(tipo2, times(1)).getFoco();
    }

    @Test
    public void testCalcularPontuacaoPorTipoInvestimentoComMock() {
        Investimento investimento1 = Mockito.mock(Investimento.class);
        Investimento investimento2 = Mockito.mock(Investimento.class);

        TipoProdutoInvestimentoEnum tipo1 = Mockito.mock(TipoProdutoInvestimentoEnum.class);
        TipoProdutoInvestimentoEnum tipo2 = Mockito.mock(TipoProdutoInvestimentoEnum.class);

        when(investimento1.getTipo()).thenReturn(tipo1);
        when(investimento2.getTipo()).thenReturn(tipo2);

        when(tipo1.getPontuacao()).thenReturn(5);
        when(tipo2.getPontuacao()).thenReturn(7);

        Double pontuacao = SimulacaoUtil.calculaPontuacaoPorTipoinvestimento(List.of(investimento1, investimento2));

        assertEquals(6.0, pontuacao);
    }

    @Test
    public void testCalcularIncidenciaTipoInvestimentoComMock() {
        Investimento investimento1 = Mockito.mock(Investimento.class);
        Investimento investimento2 = Mockito.mock(Investimento.class);

        when(investimento1.getRisco()).thenReturn(RiscoInvestimentoEnum.ALTO);
        when(investimento2.getRisco()).thenReturn(RiscoInvestimentoEnum.ALTO);

        RiscoInvestimentoEnum risco = SimulacaoUtil.calculaIncidenciaTipoInvestimento(List.of(investimento1, investimento2));

        assertEquals(RiscoInvestimentoEnum.ALTO, risco);
        verify(investimento1, times(1)).getRisco();
        verify(investimento2, times(1)).getRisco();
    }

    @Test
    public void testCalcularPontuacaoFinalInvestidorComMock() {
        Double pontuacaoMedia = 6.0;
        FocoProdutoInvestimentoEnum foco = FocoProdutoInvestimentoEnum.RENTABILIDADE;
        RiscoInvestimentoEnum risco = RiscoInvestimentoEnum.ALTO;

        Double resultado = SimulacaoUtil.calculaPontuacaoFinalInvestidor(pontuacaoMedia, foco, risco);

        assertEquals(pontuacaoMedia + foco.getPontuacao() + risco.getPontuacao(), resultado);
    }
}

