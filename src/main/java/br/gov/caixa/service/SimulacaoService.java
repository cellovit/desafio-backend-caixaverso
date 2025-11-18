package br.gov.caixa.service;

import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.simulacao.HistoricoSimulacaoResponseDto;
import br.gov.caixa.dto.response.simulacao.ResultadoSimulacaoInvestimentoResponseDto;
import br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface SimulacaoService {

    ResultadoSimulacaoInvestimentoResponseDto simularInvestimento(SimularInvestimentoRequestDto requestDto);
    List<HistoricoSimulacaoResponseDto> obterHistoricoSimulacoes(PageParams pageParams);
    List<SimulacaoProdutoDiaResponseDto> obterHistoricoSimulacoesPorProdutoDia(PageParams pageParams);

}
