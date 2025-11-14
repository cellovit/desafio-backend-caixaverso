package br.gov.caixa.filters;

import br.gov.caixa.exception.BusinessException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {
        return BusinessException.businessExceptionResponseBuilder(exception);
    }

}
