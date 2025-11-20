package br.gov.caixa.exception.handler;

import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import br.gov.caixa.exception.BusinessException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
class BusinessExceptionHandlerTest {

    @Test
    public void testToResponseDelegatesToBusinessExceptionBuilder() {
        BusinessException exception = new BusinessException(BusinessExceptionEnum.BAD_REQUEST);
        Response expectedResponse = Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro de negócio")
                .build();

        try (MockedStatic<BusinessException> mockedStatic = Mockito.mockStatic(BusinessException.class)) {
            mockedStatic.when(() -> BusinessException.businessExceptionResponseBuilder(exception))
                    .thenReturn(expectedResponse);

            BusinessExceptionHandler handler = new BusinessExceptionHandler();

            Response response = handler.toResponse(exception);

            assertEquals(expectedResponse, response);
            mockedStatic.verify(() -> BusinessException.businessExceptionResponseBuilder(exception), times(1));
        }
    }
}

