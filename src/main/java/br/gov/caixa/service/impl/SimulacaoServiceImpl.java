package br.gov.caixa.service.impl;

import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.HistoricoSimulacaoResponseDto;
import br.gov.caixa.dto.response.ResultadoSimulacaoDto;
import br.gov.caixa.dto.response.ResultadoSimulacaoInvestimentoResponseDto;
import br.gov.caixa.domain.dao.SimulacaoDao;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.mapper.ProdutoMapper;
import br.gov.caixa.service.SimulacaoService;
import br.gov.caixa.service.context.SimulacaoInvestimentoContext;
import br.gov.caixa.service.strategy.factory.SimulacaoInvestimentoStrategyFactory;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@ApplicationScoped
public class SimulacaoServiceImpl implements SimulacaoService {

    @Inject
    SimulacaoDao simulacaoDao;

    @Inject
    ProdutoInvestimentoRepository produtoInvestimentoRepository;

    @Inject
    SimulacaoInvestimentoContext context;

    @Inject
    SimulacaoInvestimentoStrategyFactory simulacaoInvestimentoStrategyFactory;

    @Inject
    ProdutoMapper produtoMapper;

    @Override
    public ResultadoSimulacaoInvestimentoResponseDto simularInvestimento(SimularInvestimentoRequestDto requestDto) {
        var strategy = simulacaoInvestimentoStrategyFactory.getStrategy(requestDto.tipoProduto());
        var produtoInvestimento = produtoInvestimentoRepository.findByTipo(requestDto.tipoProduto())
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.PRODUTO_INVESTIMENTO_FORA_DO_ESCOPO));

        BigDecimal valorFinal = context.calcularValorFinal(strategy, requestDto.valor(), produtoInvestimento.getRentabilidade(), requestDto.prazomeses());
        BigDecimal rentabilidadeEfetiva = context.calcularRentabilidadeEfetiva(strategy, requestDto.valor(), valorFinal);

        var produtoValidado = produtoMapper.toProdutoRecomendadoResponseDto(produtoInvestimento);

        var resultado = ResultadoSimulacaoInvestimentoResponseDto.builder()
                .produtoValidado(produtoValidado)
                .resultadoSimulacaoDto(buildResultadoSimulacaoDto(valorFinal, rentabilidadeEfetiva, requestDto.prazomeses()))
                .dataSimulacao(java.time.ZonedDateTime.now())
                .build();

        return resultado;
    }

    @Override
    public List<HistoricoSimulacaoResponseDto> obterHistoricoSimulacoes(PageParams pageParams) {
        simulacaoDao.findPaginado(pageParams);
        return null;
    }

    private ResultadoSimulacaoDto buildResultadoSimulacaoDto(BigDecimal valorFinal, BigDecimal rentabilidadeEfetiva, Integer prazoMeses) {
        return new ResultadoSimulacaoDto(
                valorFinal,
                rentabilidadeEfetiva,
                prazoMeses
        );
    }

}
