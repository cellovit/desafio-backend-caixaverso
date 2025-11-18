package br.gov.caixa.mapper;

import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.dto.response.HistoricoInvestimentoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface InvestimentoMapper {

    @Mapping(source = "valorAplicado", target = "valor")
    @Mapping(source = "dataAplicacao", target = "data")
    HistoricoInvestimentoResponseDto toHistoricoInvestimentoResponseDto(Investimento investimento);

}
