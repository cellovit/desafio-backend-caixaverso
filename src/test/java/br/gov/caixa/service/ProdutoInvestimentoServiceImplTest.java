package br.gov.caixa.service;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.response.cliente.ProdutoRecomendadoResponseDto;
import br.gov.caixa.mapper.ProdutoInvestimentoMapper;
import br.gov.caixa.service.impl.ProdutoInvestimentoServiceImpl;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class ProdutoInvestimentoServiceImplTest {

    @InjectMock
    ProdutoInvestimentoRepository produtoInvestimentoRepository;

    // https://github.com/quarkusio/quarkus/issues/47227
//    @Mock
//    ProdutoInvestimentoMapper mapper;

    @Inject
    ProdutoInvestimentoServiceImpl produtoInvestimentoService;

    @Test
    public void testBuscarProdutosRecomendadosPorPerfil() {
        PerfilInvestidorEnum perfilEnum = PerfilInvestidorEnum.MODERADO;
        PageParams pageParams = new PageParams(1, 10);

        ProdutoInvestimento produto = new ProdutoInvestimento();
        produto.setNome("CDB Banco XP");

        ProdutoRecomendadoResponseDto dto = new ProdutoRecomendadoResponseDto(1L, "CDB Banco XP", "CDB", BigDecimal.valueOf(0.5), "BAIXO");

        when(produtoInvestimentoRepository.findByRisco(any(), eq(pageParams)))
                .thenReturn(List.of(produto));

//        when(mapper.toProdutoRecomendadoResponseDto(produto)).thenReturn(dto);

        List<ProdutoRecomendadoResponseDto> result =
                produtoInvestimentoService.buscarProdutosRecomendadosPorPerfil(perfilEnum, pageParams);

        assertEquals(1, result.size());
//        assertEquals("CDB Banco XP", result.get(0).getTitulo());

        verify(produtoInvestimentoRepository, atLeastOnce()).findByRisco(any(), eq(pageParams));
//        verify(mapper, times(1)).toProdutoRecomendadoResponseDto(produto);
    }
}
