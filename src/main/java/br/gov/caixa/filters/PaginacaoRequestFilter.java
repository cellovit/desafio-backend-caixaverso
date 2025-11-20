package br.gov.caixa.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class PaginacaoRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String pageParam = requestContext.getUriInfo().getQueryParameters().getFirst("page");
        String sizeParam = requestContext.getUriInfo().getQueryParameters().getFirst("size");

        int page = pageParam != null ? Integer.parseInt(pageParam) : 0;
        int size = sizeParam != null ? Integer.parseInt(sizeParam) : 10;

        // Armazena como propriedades da requisição
        requestContext.setProperty("page", page);
        requestContext.setProperty("size", size);

        System.out.println("Interceptado: page=" + page + ", size=" + size);
    }
}
