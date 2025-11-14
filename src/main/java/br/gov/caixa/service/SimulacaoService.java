package br.gov.caixa.service;

import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.HistoricoSimulacaoResponseDto;
import br.gov.caixa.dto.response.ResultadoSimulacaoInvestimentoResponseDto;

import java.util.List;

public interface SimulacaoService {

    ResultadoSimulacaoInvestimentoResponseDto simularInvestimento(SimularInvestimentoRequestDto requestDto);
    List<HistoricoSimulacaoResponseDto> obterHistoricoSimulacoes(PageParams pageParams);

}
