package br.gov.caixa.mapper;

import br.gov.caixa.domain.entity.Simulacao;
import br.gov.caixa.dto.response.simulacao.HistoricoSimulacaoResponseDto;

import br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaQueryResultDto;
import br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface SimulacaoMapper {

    @Mapping(source = "investidor.id", target = "clienteId")
    HistoricoSimulacaoResponseDto toHistoricoSimulacaoResponseDto(Simulacao simulacao);

    SimulacaoProdutoDiaResponseDto queryResultToResponse(SimulacaoProdutoDiaQueryResultDto simulacaoProdutoDiaQueryResultDto);

}
