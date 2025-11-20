package br.gov.caixa.service.impl;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.entity.Simulacao;
import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import br.gov.caixa.domain.repository.CustomEntityRepository;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.domain.repository.SimulacaoRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.simulacao.*;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.mapper.ProdutoInvestimentoMapper;
import br.gov.caixa.mapper.SimulacaoMapper;
import br.gov.caixa.service.MotorRecomendacaoService;
import br.gov.caixa.service.SimulacaoService;
import br.gov.caixa.service.context.SimulacaoInvestimentoContext;
import br.gov.caixa.service.strategy.factory.SimulacaoInvestimentoStrategyFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.gov.caixa.util.RiscoInvestimentoUtil.defineRiscoPorPerfil;

@Slf4j
@ApplicationScoped
public class SimulacaoServiceImpl implements SimulacaoService {

    @Inject
    SimulacaoRepository simulacaoRepository;

    @Inject
    ProdutoInvestimentoRepository produtoInvestimentoRepository;

    @Inject
    SimulacaoInvestimentoContext context;

    @Inject
    SimulacaoInvestimentoStrategyFactory simulacaoInvestimentoStrategyFactory;

    @Inject
    ProdutoInvestimentoMapper produtoInvestimentoMapper;

    @Inject
    SimulacaoMapper simulacaoMapper;

    @Inject
    InvestidorRepository investidorRepository;

    @Inject
    CustomEntityRepository customEntityRepository;

    @Inject
    MotorRecomendacaoService motorRecomendacaoService;

    @Override
    @Transactional
    public ResultadoSimulacaoInvestimentoResponseDto simularInvestimento(SimularInvestimentoRequestDto requestDto) {
        // se a requisição nao recebe o parametro de tipo de produto, faz a simulação para todos os produtos do perfil do investidor
        if (StringUtils.isBlank(requestDto.tipoProduto())) {
            var investidor = investidorRepository.findByIdOptional(requestDto.clienteId())
                    .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.INVESTIDOR_NAO_ENCONTRADO));

            var perfil = Optional.ofNullable(investidor.getPerfilInvestidor());

            // se ele possuir perfil, recomendar e simular produtos do perfil dele
            // se ele não possuir perfil, recomendar e simular produtos de perfil através do motor de recomendação
            var listRiscos = perfil.isPresent() ? defineRiscoPorPerfil(perfil.get().getTitulo())
                    : defineRiscoPorPerfil(motorRecomendacaoService.calcularPerfil(requestDto.clienteId()));

            var produtos = new ArrayList<ProdutoInvestimento>();
            listRiscos.stream().forEach((r) -> {
                produtos.addAll(produtoInvestimentoRepository.findByRisco(r));
            });

            List<ResultadoSimulacaoProdutoDto> simulacoes = new ArrayList<>();
            produtos.stream().forEach((p) -> {
                simulacoes.add(realizarSimulacao(requestDto, p));
            });

            return ResultadoSimulacaoInvestimentoResponseDto.builder()
                    .data(simulacoes)
                    .dataSimulacao(LocalDate.now())
                    .build();
        } else {
            var tipoProduto = TipoProdutoInvestimentoEnum.fromTitulo(requestDto.tipoProduto());
            var produtoInvestimento = produtoInvestimentoRepository.findFirstByTipo(tipoProduto)
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.PRODUTO_INVESTIMENTO_FORA_DO_ESCOPO));
            var resultadoSimulacaoProduto = realizarSimulacao(requestDto, produtoInvestimento);
            return ResultadoSimulacaoInvestimentoResponseDto.builder()
                    .dataSimulacao(LocalDate.now())
                    .data(List.of(resultadoSimulacaoProduto))
                    .build();
        }
    }

    @Override
    public List<HistoricoSimulacaoResponseDto> obterHistoricoSimulacoes(PageParams pageParams) {
        return simulacaoRepository.findPaginado(pageParams)
                .stream().map(simulacaoMapper::toHistoricoSimulacaoResponseDto)
                .toList();
    }

    @Override
    public List<SimulacaoProdutoDiaResponseDto> obterHistoricoSimulacoesPorProdutoDia(PageParams pageParams) {
        return customEntityRepository.simulacaoPorProdutoDia(pageParams)
                .stream().map(simulacaoMapper::queryResultToResponse)
                .toList();
    }

    @SneakyThrows
    private ResultadoSimulacaoProdutoDto realizarSimulacao(SimularInvestimentoRequestDto requestDto, ProdutoInvestimento produtoInvestimento) {
        var strategy = simulacaoInvestimentoStrategyFactory.getStrategy(produtoInvestimento.getTipo());
        BigDecimal valorFinal = context.calcularValorFinal(strategy, requestDto.valor(), produtoInvestimento.getRentabilidade(), requestDto.prazomeses());
        BigDecimal rentabilidadeEfetiva = context.calcularRentabilidadeEfetiva(strategy, requestDto.valor(), valorFinal);

        var produtoValidado = produtoInvestimentoMapper.toProdutoRecomendadoResponseDto(produtoInvestimento);
        var resultadoSimulacao = buildResultadoSimulacaoDto(valorFinal, rentabilidadeEfetiva, requestDto.prazomeses());

        var resultadoSimulacaoProduto = ResultadoSimulacaoProdutoDto.builder()
                .produtoValidado(produtoValidado)
                .resultadoSimulacaoDto(resultadoSimulacao)
                .build();
        simulacaoRepository.persist(buildSimulacaoEntityToSave(resultadoSimulacaoProduto, requestDto));
        return resultadoSimulacaoProduto;
    }

    private ResultadoSimulacaoDto buildResultadoSimulacaoDto(BigDecimal valorFinal, BigDecimal rentabilidadeEfetiva, Integer prazoMeses) {
        return new ResultadoSimulacaoDto(
                valorFinal,
                rentabilidadeEfetiva,
                prazoMeses
        );
    }

    private Simulacao buildSimulacaoEntityToSave(ResultadoSimulacaoProdutoDto resultado, SimularInvestimentoRequestDto requestDto) {
        var investidor = investidorRepository.findByIdOptional(requestDto.clienteId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.INVESTIDOR_NAO_ENCONTRADO));

        return Simulacao.builder()
                .produto(resultado.produtoValidado().nome())
                .valorInvestido(requestDto.valor())
                .valorFinal(resultado.resultadoSimulacaoDto().valorFinal())
                .prazoMeses(resultado.resultadoSimulacaoDto().prazoMeses())
                .dataSimulacao(LocalDate.now())
                .investidor(investidor)
                .build();
    }

}
