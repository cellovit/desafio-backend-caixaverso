package br.gov.caixa.filters;

import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import br.gov.caixa.service.TelemetryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.math.BigDecimal;
import java.time.Instant;

@Provider
@Slf4j
public class TelemetryRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    TelemetryService telemetryService;

    private static final ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();

    @Override
    public void filter(ContainerRequestContext requestContext) {
        setStopWatch();
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        StopWatch stopWatch = stopWatchThreadLocal.get();
        if (stopWatch != null) {
            stopWatch.stop();
            salvaRequisicao(stopWatch.getTime(), buildEndpointString(requestContext));
            stopWatchThreadLocal.remove();
        }
    }

    private void setStopWatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stopWatchThreadLocal.set(stopWatch);
    }

    private void salvaRequisicao(long tempoMs, String endpoint) {
        log.info("Tempo de resposta: {}ms para {}", tempoMs, endpoint);
        var entity = TelemetryMetrics.builder()
                .dataColeta(Instant.now())
                .endpoint(endpoint)
                .tempoResposta(BigDecimal.valueOf(tempoMs))
                .build();
        telemetryService.insert(entity);
    }

    private String buildEndpointString(ContainerRequestContext requestContext) {
        var endpoint = new StringBuilder(requestContext.getUriInfo().getPath());
        requestContext.getUriInfo().getPathParameters().forEach((nomeParametro, valorParametro) -> valorParametro.forEach(v -> {
            var index = endpoint.indexOf(v);
            endpoint.replace(index, index + v.length(), String.format("{%s}", nomeParametro));
        }));
        return endpoint.toString();
    }
}
