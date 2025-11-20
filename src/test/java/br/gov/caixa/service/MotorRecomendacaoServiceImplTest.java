package br.gov.caixa.service;

import br.gov.caixa.domain.entity.Investidor;
import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.enums.*;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.service.impl.MotorRecomendacaoServiceImpl;
import br.gov.caixa.util.SimulacaoUtil;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class MotorRecomendacaoServiceImplTest {

    @InjectMock
    InvestidorRepository investidorRepository;

    @Inject
    MotorRecomendacaoServiceImpl motorRecomendacaoService;

    @Test
    public void testCalcularPerfil_ComInvestimentos() {
        Investidor investidor = new Investidor();
        investidor.setId(1L);

        Investimento inv1 = new Investimento();
        inv1.setTipo(TipoProdutoInvestimentoEnum.CDB);

        Investimento inv2 = new Investimento();
        inv2.setTipo(TipoProdutoInvestimentoEnum.ACAO);

        investidor.setInvestimentos(List.of(inv1, inv2));

        when(investidorRepository.findByIdOptional(anyLong())).thenReturn(Optional.of(investidor));

        try (MockedStatic<SimulacaoUtil> utilities = Mockito.mockStatic(SimulacaoUtil.class)) {
            utilities.when(() -> SimulacaoUtil.calculaIncidenciaTipoInvestimento(any()))
                    .thenReturn(RiscoInvestimentoEnum.BAIXO);
            utilities.when(() -> SimulacaoUtil.calculaPontuacaoPorTipoinvestimento(any())).thenReturn(5.0);
            utilities.when(() -> SimulacaoUtil.calculaFoco(any())).thenReturn(FocoProdutoInvestimentoEnum.LIQUIDEZ);

            PerfilInvestidorEnum perfil = motorRecomendacaoService.calcularPerfil(1L);

            assertEquals(PerfilInvestidorEnum.CONSERVADOR, perfil);
            verify(investidorRepository, times(1)).findByIdOptional(1L);
        }

    }

    @Test
    public void testCalcularPerfil_SemInvestimentos() {
        Investidor investidor = new Investidor();
        investidor.setId(2L);
        investidor.setInvestimentos(List.of()); // sem investimentos

        when(investidorRepository.findByIdOptional(2L)).thenReturn(Optional.of(investidor));

        PerfilInvestidorEnum perfil = motorRecomendacaoService.calcularPerfil(2L);

        // Como não há investimentos, o perfil deve ser calculado com base no limiar padrão
        assertEquals(PerfilInvestidorEnum.CONSERVADOR, perfil);
        verify(investidorRepository, times(1)).findByIdOptional(2L);
    }

    @Test
    public void testCalcularPerfil_InvestidorNaoEncontrado() {
        when(investidorRepository.findByIdOptional(99L)).thenReturn(Optional.empty());

        try {
            motorRecomendacaoService.calcularPerfil(99L);
        } catch (BusinessException ex) {
            assertEquals(BusinessExceptionEnum.INVESTIDOR_NAO_ENCONTRADO, ex.getBusinessError());
        }

        verify(investidorRepository, times(1)).findByIdOptional(99L);
    }
}
