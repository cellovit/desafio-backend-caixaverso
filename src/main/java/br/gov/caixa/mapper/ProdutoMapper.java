package br.gov.caixa.mapper;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.dto.response.ProdutoRecomendadoResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface ProdutoMapper {
    ProdutoRecomendadoResponseDto toProdutoRecomendadoResponseDto(ProdutoInvestimento produto);
}
