package br.gov.caixa.service.impl;

import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.HistoricoSimulacaoResponseDto;
import br.gov.caixa.dto.response.ResultadoSimulacaoInvestimentoResponseDto;
import br.gov.caixa.repository.SimulacaoRepository;
import br.gov.caixa.service.SimulacaoService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ApplicationScoped
public class SimulacaoServiceImpl implements SimulacaoService {

    @Inject
    SimulacaoRepository simulacaoRepository;

    @Override
    public ResultadoSimulacaoInvestimentoResponseDto simularInvestimento(SimularInvestimentoRequestDto requestDto) {
        return null;
    }

    @Override
    @WithSpan("updateProduct")
    public List<HistoricoSimulacaoResponseDto> obterHistoricoSimulacoes(PageParams pageParams) {
        simulacaoRepository.findPaginado(pageParams);
        return null;
    }

}
