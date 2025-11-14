package br.gov.caixa.resources;

import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import br.gov.caixa.exception.BusinessException;
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
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Slf4j
@Timeout
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
@Authenticated
public class SimulacaoInvestimentoResource extends AbstractResource {

    @Inject
    SimulacaoService simulacaoService;

    @Path("/simulacoes")
    @GET
    @RunOnVirtualThread
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
            simulacaoService.obterHistoricoSimulacoes(pageParams);
            log.info("Simulação de investimento realizada com sucesso.");
            return Response.ok("Simulação de investimento concluída.").build();
        });
    }

    @Path("/simular-investimento")
    public Response simularInvestimento(@Valid SimularInvestimentoRequestDto requestDto) {
        return processAndLog(() -> {
            log.info("Simulação de investimento realizada com sucesso.");
            return Response.ok("Simulação de investimento concluída.").build();
        });
    }

}
