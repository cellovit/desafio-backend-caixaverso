package br.gov.caixa.exception.handler;

import br.gov.caixa.exception.BusinessException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@QuarkusTest
class GlobalExceptionHandlerTest {

    @Test
    public void testToResponseDelegatesToBusinessExceptionBuilder() {
        Exception exception = new Exception("Erro genérico");
        Response expectedResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro genérico")
                .build();

        try (MockedStatic<BusinessException> mockedStatic = Mockito.mockStatic(BusinessException.class)) {
            mockedStatic.when(() -> BusinessException.businessExceptionResponseBuilder(exception))
                    .thenReturn(expectedResponse);

            GlobalExceptionHandler handler = new GlobalExceptionHandler();

            Response response = handler.toResponse(exception);

            assertEquals(expectedResponse, response);
            mockedStatic.verify(() -> BusinessException.businessExceptionResponseBuilder(exception), times(1));
        }
    }
}

