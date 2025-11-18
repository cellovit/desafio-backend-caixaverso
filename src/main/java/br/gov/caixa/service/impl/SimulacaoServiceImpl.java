package br.gov.caixa.service.impl;

import br.gov.caixa.domain.dao.SimulacaoPorDiaDao;
import br.gov.caixa.domain.entity.Simulacao;
import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import br.gov.caixa.domain.repository.CustomEntityRepository;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.simulacao.HistoricoSimulacaoResponseDto;
import br.gov.caixa.dto.response.simulacao.ResultadoSimulacaoDto;
import br.gov.caixa.dto.response.simulacao.ResultadoSimulacaoInvestimentoResponseDto;
import br.gov.caixa.domain.dao.SimulacaoDao;
import br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaResponseDto;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.mapper.ProdutoMapper;
import br.gov.caixa.mapper.SimulacaoMapper;
import br.gov.caixa.service.SimulacaoService;
import br.gov.caixa.service.context.SimulacaoInvestimentoContext;
import br.gov.caixa.service.strategy.factory.SimulacaoInvestimentoStrategyFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Inject
    SimulacaoMapper simulacaoMapper;

    @Inject
    InvestidorRepository investidorRepository;

    @Inject
    CustomEntityRepository customEntityRepository;

    @Override
    @Transactional
    public ResultadoSimulacaoInvestimentoResponseDto simularInvestimento(SimularInvestimentoRequestDto requestDto) {
        var strategy = simulacaoInvestimentoStrategyFactory.getStrategy(requestDto.tipoProduto());
        var produtoInvestimento = produtoInvestimentoRepository.findByTipo(requestDto.tipoProduto())
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.PRODUTO_INVESTIMENTO_FORA_DO_ESCOPO));

        BigDecimal valorFinal = context.calcularValorFinal(strategy, requestDto.valor(), produtoInvestimento.getRentabilidade(), requestDto.prazomeses());
        BigDecimal rentabilidadeEfetiva = context.calcularRentabilidadeEfetiva(strategy, requestDto.valor(), valorFinal);

        var produtoValidado = produtoMapper.toProdutoRecomendadoResponseDto(produtoInvestimento);
        var resultadoSimulacao = buildResultadoSimulacaoDto(valorFinal, rentabilidadeEfetiva, requestDto.prazomeses());

        var resultado = ResultadoSimulacaoInvestimentoResponseDto.builder()
                .produtoValidado(produtoValidado)
                .resultadoSimulacaoDto(resultadoSimulacao)
                .dataSimulacao(java.time.LocalDate.now())
                .build();
        simulacaoDao.persist(buildSimulacaoEntityToSave(resultado, requestDto));

        return resultado;
    }

    @Override
    public List<HistoricoSimulacaoResponseDto> obterHistoricoSimulacoes(PageParams pageParams) {
        return simulacaoDao.findPaginado(pageParams)
                .stream().map(simulacaoMapper::toHistoricoSimulacaoResponseDto)
                .toList();
    }

    @Override
    public List<SimulacaoProdutoDiaResponseDto> obterHistoricoSimulacoesPorProdutoDia(PageParams pageParams) {
        return customEntityRepository.simulacaoPorProdutoDia(pageParams)
                .stream().map(simulacaoMapper::queryResultToResponse)
                .toList();
    }

    private ResultadoSimulacaoDto buildResultadoSimulacaoDto(BigDecimal valorFinal, BigDecimal rentabilidadeEfetiva, Integer prazoMeses) {
        return new ResultadoSimulacaoDto(
                valorFinal,
                rentabilidadeEfetiva,
                prazoMeses
        );
    }

    private Simulacao buildSimulacaoEntityToSave(ResultadoSimulacaoInvestimentoResponseDto resultado, SimularInvestimentoRequestDto requestDto) {
        var investidor = investidorRepository.findByIdOptional(requestDto.clienteId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.INVESTIDOR_NAO_ENCONTRADO));

        return Simulacao.builder()
                .produto(resultado.produtoValidado().nome())
                .valorInvestido(requestDto.valor())
                .valorFinal(resultado.resultadoSimulacaoDto().valorFinal())
                .prazoMeses(resultado.resultadoSimulacaoDto().prazoMeses())
                .dataSimulacao(resultado.dataSimulacao())
                .investidor(investidor)
                .build();
    }

}
