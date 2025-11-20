package br.gov.caixa.service.impl;

import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.response.cliente.ProdutoRecomendadoResponseDto;
import br.gov.caixa.mapper.ProdutoInvestimentoMapper;
import br.gov.caixa.service.ProdutoInvestimentoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static br.gov.caixa.util.SimulacaoUtil.defineRiscoPorPerfil;

@ApplicationScoped
@Slf4j
public class ProdutoInvestimentoServiceImpl implements ProdutoInvestimentoService {

    @Inject
    ProdutoInvestimentoRepository produtoInvestimentoRepository;

    @Inject
    ProdutoInvestimentoMapper mapper;

    @Override
    public List<ProdutoRecomendadoResponseDto> buscarProdutosRecomendadosPorPerfil(PerfilInvestidorEnum perfilEnum, PageParams pageParams) {
        var risco = defineRiscoPorPerfil(perfilEnum);
        return risco.stream()
                .flatMap(r -> produtoInvestimentoRepository.findByRisco(r, pageParams).stream())
                .map(mapper::toProdutoRecomendadoResponseDto)
                .collect(Collectors.toList());

    }

}
