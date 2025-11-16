package br.gov.caixa.resources;

import br.gov.caixa.service.SimulacaoService;
import br.gov.caixa.service.TelemetryService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Slf4j
@Timeout(5000)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
@Authenticated
//@SecurityRequirement(name = "Keycloak")
public class TelemetryResource extends AbstractResource {

    @Inject
    TelemetryService telemetryService;

    @Path("/telemetria")
    @GET
//    @RunOnVirtualThread
    public Response getTelemetryData() {
        return processAndLog(() -> {
            log.info("Telemetry data collected successfully.");
            return Response.ok(telemetryService.collectTelemetryData()).build();
        });
    }

}
