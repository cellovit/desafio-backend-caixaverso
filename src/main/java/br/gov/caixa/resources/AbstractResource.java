package br.gov.caixa.resources;

import br.gov.caixa.exception.BusinessException;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.util.function.Supplier;

@Slf4j
public class AbstractResource {

//    @Inject
//    MeterRegistry registry;
//
//    @Inject
//    @Context
//    UriInfo uriInfo;

    protected <T extends Response> T processAndLog(Supplier<T> processing) {
        try {
            log.info("Iniciando processamento de requisição.");
            var stopwatch = new StopWatch();
            stopwatch.start();
            var p = processing.get();
//            registry.timer(uriInfo.getPath()).record(stopwatch.getDuration());
            stopwatch.stop();
            log.info("Processamento de requisição finalizado com sucesso.");
            return p;
        } catch (Exception ex) {
            log.error("Erro durante o processamento da requisição: {}", ex.getMessage(), ex);
            throw BusinessException.of(ex);
        }
    }

}
