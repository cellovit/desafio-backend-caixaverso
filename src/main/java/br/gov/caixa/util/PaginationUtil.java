package br.gov.caixa.util;

import br.gov.caixa.dto.PageParams;
import io.quarkus.panache.common.Page;
import jakarta.inject.Singleton;

@Singleton
public class PaginationUtil {

    public static Page getPage(PageParams pageParams){
        return Page.of(pageParams.page() - 1, pageParams.pageSize());
    }

    public static Page getPage(int page, int pageSize){
        return Page.of(page - 1, pageSize);
    }

}
