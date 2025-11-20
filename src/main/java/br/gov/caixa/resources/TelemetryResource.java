package br.gov.caixa.resources;

import br.gov.caixa.dto.response.telemetry.TelemetryResponseDto;
import br.gov.caixa.exception.response.ErrorResponse;
import br.gov.caixa.service.TelemetryService;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.LocalDate;

import static br.gov.caixa.domain.constants.HttpResponseCode.*;
import static br.gov.caixa.domain.constants.HttpResponseCode.TIMEOUT_524;
import static br.gov.caixa.domain.constants.HttpResponseCode.UNAUTHORIZED_401;
import static br.gov.caixa.domain.constants.HttpResponseDescription.*;
import static br.gov.caixa.domain.constants.HttpResponseDescription.UNAUTHORIZED_DESCRIPTION;

@Slf4j
@Timeout(10000)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/telemetria")
@Authenticated
@SecurityRequirement(name = "Keycloak")
public class TelemetryResource extends AbstractResource {

    @Inject
    TelemetryService telemetryService;

    @GET
    @RunOnVirtualThread
    @Tag(name = "Dados de telemetria", description = "Obtem uma lista de chamadas realizadas na API")
    @Operation(summary = "Dados de telemetria", description = "Obtem uma lista de chamadas realizadas na API")
    @APIResponse(responseCode = OK_200, description = "Lista de chamadas realizadas na API obtida com sucesso", content = @Content(schema = @Schema(implementation = TelemetryResponseDto.class)))
    @APIResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = INTERNAL_SERVER_ERROR_500, description = INTERNAL_SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = TIMEOUT_524, description = TIMEOUT_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Response getTelemetryData(
            @QueryParam("dataInicio")
            LocalDate dataInicio,

            @QueryParam("dataFim")
            LocalDate dataFim
    ) {
        return processAndLog(() -> Response.ok(telemetryService.collectTelemetryData(dataInicio, dataFim)).build());
    }

}
