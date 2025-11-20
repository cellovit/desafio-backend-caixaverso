package br.gov.caixa.resources;

import br.gov.caixa.service.SimulacaoService;
import br.gov.caixa.service.TelemetryService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.time.LocalDate;

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
    public Response getTelemetryData(
            @QueryParam("dataInicio")
            LocalDate dataInicio,

            @QueryParam("dataFim")
            LocalDate dataFim
    ) {
        return processAndLog(() -> Response.ok(telemetryService.collectTelemetryData(dataInicio, dataFim)).build());
    }

}
