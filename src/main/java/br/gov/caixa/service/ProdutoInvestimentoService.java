package br.gov.caixa.service;

import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.response.cliente.ProdutoRecomendadoResponseDto;

import java.util.List;

public interface ProdutoInvestimentoService {

    List<ProdutoRecomendadoResponseDto> buscarProdutosRecomendadosPorPerfil(PerfilInvestidorEnum perfilEnum, PageParams pageParams);

}
