package br.gov.caixa.exception;

import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static org.apache.commons.lang3.exception.ExceptionUtils.throwableOfType;

@Getter
@Slf4j
public class BusinessException extends RuntimeException {

    private final BusinessErrorInterface businessError;

    private BusinessException(BusinessErrorInterface businessError) {
        super(businessError.getDetailMessage());
        this.businessError = businessError;
        log.error("BusinessException : Code={}, DetailMessage={}",
                businessError.getCode(),
                businessError.getDetailMessage());
    }

    public static Response businessExceptionResponseBuilder(BusinessException businessException) {
        return Response.status(Integer.parseInt(businessException.getBusinessError().getCode()))
                .entity(buildErrorBody(businessException.getBusinessError()) )
                .build();
    }

    public static BusinessException of(Throwable throwable) {
        return Optional.ofNullable(throwableOfType(throwable, BusinessException.class))
                .orElse(new BusinessException(BusinessExceptionEnum.INTERNAL_SERVER_ERROR));
    }

    private static ErrorResponse buildErrorBody(BusinessErrorInterface businessError) {
        return new ErrorResponse(
                businessError.getCode(),
                businessError.getTitle(),
                businessError.getDetailMessage()
        );
    }
}
