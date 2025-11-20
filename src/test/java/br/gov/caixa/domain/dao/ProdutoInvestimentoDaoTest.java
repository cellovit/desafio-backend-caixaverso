package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.enums.RiscoInvestimentoEnum;
import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import br.gov.caixa.dto.PageParams;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
@ExtendWith(MockitoExtension.class)
public class ProdutoInvestimentoDaoTest {

    @InjectMock
    ProdutoInvestimentoDao dao;

    @Mock
    PanacheQuery<ProdutoInvestimento> query;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testFindFirstByTipo() {
        ProdutoInvestimento produto = new ProdutoInvestimento();
        produto.setTipo(TipoProdutoInvestimentoEnum.CDB);

        when(dao.find(anyString(), any(Object.class))).thenReturn(query);
        when(dao.findFirstByTipo(any())).thenReturn(Optional.of(produto));

        Optional<ProdutoInvestimento> result = dao.findFirstByTipo(TipoProdutoInvestimentoEnum.CDB);

        assertEquals(TipoProdutoInvestimentoEnum.CDB, result.get().getTipo());
    }

    @Test
    public void testFindByRisco() {
        ProdutoInvestimento produto = new ProdutoInvestimento();
        produto.setRisco(RiscoInvestimentoEnum.BAIXO);

        doReturn(query).when(dao).find("risco", RiscoInvestimentoEnum.BAIXO);
        when(dao.findByRisco(any())).thenReturn(List.of(produto));

        List<ProdutoInvestimento> result = dao.findByRisco(RiscoInvestimentoEnum.BAIXO);

        assertEquals(1, result.size());
        assertEquals(RiscoInvestimentoEnum.BAIXO, result.get(0).getRisco());
    }

    @Test
    public void testFindByRiscoWithPageParams() {
        PageParams pageParams = new PageParams(1, 5);
        ProdutoInvestimento produto = new ProdutoInvestimento();
        produto.setRisco(RiscoInvestimentoEnum.ALTO);

        when(dao.findByRisco(any(), any())).thenReturn(List.of(produto));

        List<ProdutoInvestimento> result = dao.findByRisco(RiscoInvestimentoEnum.ALTO, pageParams);

        assertEquals(1, result.size());
        assertEquals(RiscoInvestimentoEnum.ALTO, result.get(0).getRisco());
    }
}

