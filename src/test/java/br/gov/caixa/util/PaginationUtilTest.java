package br.gov.caixa.util;

import br.gov.caixa.dto.PageParams;
import io.quarkus.panache.common.Page;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PaginationUtilTest {

    @Test
    public void testGetPageWithPageParams() {
        // Mock de PageParams
        PageParams mockParams = Mockito.mock(PageParams.class);
        Mockito.when(mockParams.page()).thenReturn(2);
        Mockito.when(mockParams.pageSize()).thenReturn(20);

        Page page = PaginationUtil.getPage(mockParams);

        assertEquals(1, page.index);   // porque subtrai 1
        assertEquals(20, page.size);
    }

    @Test
    public void testGetPageWithIntValues() {
        Page page = PaginationUtil.getPage(3, 15);

        assertEquals(2, page.index);   // porque subtrai 1
        assertEquals(15, page.size);
    }
}
