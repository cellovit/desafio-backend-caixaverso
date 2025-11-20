package br.gov.caixa.resources;

import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.dto.response.simulacao.HistoricoSimulacaoResponseDto;
import br.gov.caixa.dto.response.simulacao.ResultadoSimulacaoInvestimentoResponseDto;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.exception.ErrorResponse;
import br.gov.caixa.service.SimulacaoService;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
import static br.gov.caixa.domain.constants.HttpResponseDescription.*;

@Slf4j
@Timeout(10000)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
@Authenticated
@SecurityRequirement(name = "Keycloak")
public class SimulacaoInvestimentoResource extends AbstractResource {

    @Inject
    SimulacaoService simulacaoService;

    @Path("/simulacoes")
    @GET
    @RunOnVirtualThread
    @Tag(name = "Histórico de Simulações de Investimento", description = "Histórico de Simulações de Investimento")
    @Operation(summary = "Obter histórico de simulações de investimento", description = "Retorna uma lista paginada com o histórico de simulações de investimento realizadas pelo usuário.")
    @APIResponse(responseCode = OK_200, description = "Histórico de simulações obtido com sucesso.", content = @Content(schema = @Schema(implementation = HistoricoSimulacaoResponseDto.class)))
    @APIResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = INTERNAL_SERVER_ERROR_500, description = INTERNAL_SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = TIMEOUT_524, description = TIMEOUT_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Response getHistoricoSimulacoes(
            @QueryParam("page")
            @DefaultValue("1")
            @Min(1)
            String page,

            @QueryParam("pageSize")
            @DefaultValue("10")
            @Max(100)
            String pageSize
    ) throws BusinessException {
        return processAndLog(() -> {
            var pageParams = new PageParams(Integer.parseInt(page), Integer.parseInt(pageSize));
            return Response.ok(simulacaoService.obterHistoricoSimulacoes(pageParams)).build();
        });
    }

    @Path("/simulacoes/por-produto-dia")
    @GET
    @RunOnVirtualThread
    @Tag(name = "Histórico de Simulações de Investimento", description = "Histórico de Simulações de Investimento")
    @Operation(summary = "Obter histórico de simulações de investimento", description = "Retorna uma lista paginada com o histórico de simulações de investimento realizadas pelo usuário.")
    @APIResponse(responseCode = OK_200, description = "Histórico de simulações obtido com sucesso.", content = @Content(schema = @Schema(implementation = ResultadoSimulacaoInvestimentoResponseDto.class)))
    @APIResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = INTERNAL_SERVER_ERROR_500, description = INTERNAL_SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = TIMEOUT_524, description = TIMEOUT_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @APIResponse(responseCode = UNAUTHORIZED_401, description = UNAUTHORIZED_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public Response getHistoricoSimulacoesPorProdutoDia(
            @QueryParam("page")
            @DefaultValue("1")
            @Min(1)
            String page,

            @QueryParam("pageSize")
            @DefaultValue("10")
            @Max(100)
            String pageSize
    ) throws BusinessException {
        return processAndLog(() -> {
            var pageParams = new PageParams(Integer.parseInt(page), Integer.parseInt(pageSize));
            return Response.ok(simulacaoService.obterHistoricoSimulacoesPorProdutoDia(pageParams))
                    .build();
        });
    }

    @POST
    @Path("/simular-investimento")
    @RunOnVirtualThread
    public Response simularInvestimento(@Valid SimularInvestimentoRequestDto requestDto) throws BusinessException {
        return processAndLog(() -> {
            return Response.ok(simulacaoService.simularInvestimento(requestDto)).build();
        });
    }

}
