package br.gov.caixa.resources;

import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.response.PerfilRiscoResponseDto;
import br.gov.caixa.dto.response.simulacao.HistoricoSimulacaoResponseDto;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.exception.ErrorResponse;
import br.gov.caixa.service.InvestidorService;
import br.gov.caixa.service.SimulacaoService;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

import static br.gov.caixa.domain.constants.HttpResponseCode.*;
import static br.gov.caixa.domain.constants.HttpResponseCode.TIMEOUT_524;
import static br.gov.caixa.domain.constants.HttpResponseCode.UNAUTHORIZED_401;
import static br.gov.caixa.domain.constants.HttpResponseDescription.*;
import static br.gov.caixa.domain.constants.HttpResponseDescription.UNAUTHORIZED_DESCRIPTION;

@Slf4j
@Timeout(10000)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
@Authenticated
@SecurityRequirement(name = "Keycloak")
public class ClienteResource extends AbstractResource {

    @Inject
    InvestidorService investidorService;

    @Tag(name = "Perfil de investidor pelo id do cliente", description = "Busca perfil de investidor pelo id do cliente")
    @Operation(summary = "Busca perfil de investidor pelo id do cliente", description = "Busca perfil de investidor pelo id do cliente")
    @APIResponse(responseCode = OK_200, description = "", content = @Content(schema = @Schema(implementation = PerfilRiscoResponseDto.class)))
    @APIResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = INTERNAL_SERVER_ERROR_500, description = INTERNAL_SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = TIMEOUT_524, description = TIMEOUT_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @RunOnVirtualThread
    @GET
    @Path("/perfil-risco/{clienteId}")
    public Response getPerfilRiscoPorInvestidor(
            @NotNull
            @PathParam("clienteId")
            String clienteId
    ) throws BusinessException {
        return processAndLog(() -> {
            return Response.ok(investidorService.findPerfilByClienteId(Long.parseLong(clienteId)))
                    .build();
        });
    }

    @Tag(name = "Perfil de investidor pelo id do cliente", description = "Busca perfil de investidor pelo id do cliente")
    @Operation(summary = "Busca perfil de investidor pelo id do cliente", description = "Busca perfil de investidor pelo id do cliente")
    @APIResponse(responseCode = OK_200, description = "", content = @Content(schema = @Schema(implementation = PerfilRiscoResponseDto.class)))
    @APIResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = INTERNAL_SERVER_ERROR_500, description = INTERNAL_SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = TIMEOUT_524, description = TIMEOUT_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @RunOnVirtualThread
    @GET
    @Path("/investimentos/{clienteId}")
    public Response getInvestimentosPorInvestidor(
            @NotNull
            @PathParam("clienteId")
            String clienteId
    ) throws BusinessException {
        return processAndLog(() -> {
            return Response.ok(investidorService.getHistoricoInvestimentos(Long.parseLong(clienteId)))
                    .build();
        });
    }

}
