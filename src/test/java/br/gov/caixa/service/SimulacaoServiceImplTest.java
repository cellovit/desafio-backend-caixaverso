package br.gov.caixa.service;

import br.gov.caixa.domain.entity.Investidor;
import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.entity.Simulacao;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import br.gov.caixa.domain.repository.CustomEntityRepository;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.domain.repository.SimulacaoRepository;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.cliente.ProdutoRecomendadoResponseDto;
import br.gov.caixa.dto.response.simulacao.ResultadoSimulacaoInvestimentoResponseDto;
import br.gov.caixa.mapper.ProdutoInvestimentoMapper;
import br.gov.caixa.mapper.SimulacaoMapper;
import br.gov.caixa.service.context.SimulacaoInvestimentoContext;
import br.gov.caixa.service.impl.SimulacaoServiceImpl;
import br.gov.caixa.service.strategy.factory.SimulacaoInvestimentoStrategyFactory;
import br.gov.caixa.service.strategy.interfaces.CalculadoraInvestimentoStrategy;
import br.gov.caixa.service.strategy.simulacao.rendafixa.CdbStrategy;
import br.gov.caixa.service.strategy.simulacao.rendavariavel.AcaoStrategy;
import br.gov.caixa.util.RiscoInvestimentoUtil;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class SimulacaoServiceImplTest {

    @InjectMock
    SimulacaoRepository simulacaoRepository;

    @InjectMock
    ProdutoInvestimentoRepository produtoInvestimentoRepository;

    @InjectMock
    SimulacaoInvestimentoContext context;

    @InjectMock
    SimulacaoInvestimentoStrategyFactory simulacaoInvestimentoStrategyFactory;

    @Mock
    CdbStrategy cdbStrategy;

    @Mock
    AcaoStrategy acaoStrategy;

//    @InjectMock
//    ProdutoInvestimentoMapper produtoInvestimentoMapper;

//    @InjectMock
//    SimulacaoMapper simulacaoMapper;

    @InjectMock
    InvestidorRepository investidorRepository;

    @InjectMock
    CustomEntityRepository customEntityRepository;

    @InjectMock
    MotorRecomendacaoService motorRecomendacaoService;

    @Inject
    SimulacaoServiceImpl simulacaoService;

    @Test
    public void testSimularInvestimento_ComTipoProduto() {
        SimularInvestimentoRequestDto requestDto = new SimularInvestimentoRequestDto(1L, BigDecimal.valueOf(1000), 12, "CDB");

        ProdutoInvestimento produto = new ProdutoInvestimento();
        produto.setTipo(TipoProdutoInvestimentoEnum.CDB);
        produto.setRentabilidade(BigDecimal.valueOf(0.01));

        when(produtoInvestimentoRepository.findFirstByTipo(TipoProdutoInvestimentoEnum.CDB))
                .thenReturn(Optional.of(produto));

//        CalculadoraInvestimentoStrategy strategy = mock(CalculadoraInvestimentoStrategy.class);

        try (MockedStatic<SimulacaoInvestimentoStrategyFactory> util = Mockito.mockStatic(SimulacaoInvestimentoStrategyFactory.class)) {
            util.when(() -> SimulacaoInvestimentoStrategyFactory.getStrategy(any())).thenReturn(cdbStrategy);

//            when(simulacaoInvestimentoStrategyFactory.getStrategy(TipoProdutoInvestimentoEnum.CDB)).thenReturn(cdbStrategy);

            when(context.calcularValorFinal(cdbStrategy, requestDto.valor(), produto.getRentabilidade(), requestDto.prazomeses()))
                    .thenReturn(BigDecimal.valueOf(1120));
            when(context.calcularRentabilidadeEfetiva(cdbStrategy, requestDto.valor(), BigDecimal.valueOf(1120)))
                    .thenReturn(BigDecimal.valueOf(0.12));

            ProdutoRecomendadoResponseDto produtoDto = ProdutoRecomendadoResponseDto.builder()
                    .nome("CDB Banco XP")
                    .build();

//        when(produtoInvestimentoMapper.toProdutoRecomendadoResponseDto(produto)).thenReturn(produtoDto);

            Investidor investidor = new Investidor();
            investidor.setId(1L);
            when(investidorRepository.findByIdOptional(1L)).thenReturn(Optional.of(investidor));

            ResultadoSimulacaoInvestimentoResponseDto response = simulacaoService.simularInvestimento(requestDto);

            assertEquals(1, response.data().size());
//        assertEquals("CDB Banco XP", response.data().get(0).produtoValidado().nome());
            assertEquals(LocalDate.now(), response.dataSimulacao());

            verify(produtoInvestimentoRepository, times(1)).findFirstByTipo(TipoProdutoInvestimentoEnum.CDB);
            verify(simulacaoRepository, times(1)).persist(any(Simulacao.class));
        }


    }

    @Test
    public void testSimularInvestimento_SemTipoProduto_InvestidorSemPerfil() {
        SimularInvestimentoRequestDto requestDto = new SimularInvestimentoRequestDto(2L, BigDecimal.valueOf(500), 6, null);

        Investidor investidor = new Investidor();
        investidor.setId(2L);
        investidor.setPerfilInvestidor(null);
        when(investidorRepository.findByIdOptional(2L)).thenReturn(Optional.of(investidor));

        when(motorRecomendacaoService.calcularPerfil(2L)).thenReturn(PerfilInvestidorEnum.MODERADO);

        ProdutoInvestimento produto = new ProdutoInvestimento();
        produto.setTipo(TipoProdutoInvestimentoEnum.ACAO);
        produto.setRentabilidade(BigDecimal.valueOf(0.02));
        when(produtoInvestimentoRepository.findByRisco(any())).thenReturn(List.of(produto));

//        CalculadoraInvestimentoStrategy strategy = mock(CalculadoraInvestimentoStrategy.class);
//        when(simulacaoInvestimentoStrategyFactory.getStrategy(TipoProdutoInvestimentoEnum.ACAO)).thenReturn(acaoStrategy);

        try (MockedStatic<SimulacaoInvestimentoStrategyFactory> util = Mockito.mockStatic(SimulacaoInvestimentoStrategyFactory.class)) {
            util.when(() -> SimulacaoInvestimentoStrategyFactory.getStrategy(any())).thenReturn(acaoStrategy);
            when(context.calcularValorFinal(acaoStrategy, requestDto.valor(), produto.getRentabilidade(), requestDto.prazomeses()))
                    .thenReturn(BigDecimal.valueOf(600));
            when(context.calcularRentabilidadeEfetiva(acaoStrategy, requestDto.valor(), BigDecimal.valueOf(600)))
                    .thenReturn(BigDecimal.valueOf(0.20));

            ProdutoRecomendadoResponseDto produtoDto = ProdutoRecomendadoResponseDto
                    .builder()
                    .nome("Ação XPTO")
                    .build();
//        when(produtoInvestimentoMapper.toProdutoRecomendadoResponseDto(produto)).thenReturn(produtoDto);

            ResultadoSimulacaoInvestimentoResponseDto response = simulacaoService.simularInvestimento(requestDto);

            assertEquals(1, response.data().size());
//        assertEquals("Ação XPTO", response.data().get(0).produtoValidado().nome());

            verify(produtoInvestimentoRepository, atLeastOnce()).findByRisco(any());
            verify(simulacaoRepository, times(1)).persist(any(Simulacao.class));
        }


    }
}

