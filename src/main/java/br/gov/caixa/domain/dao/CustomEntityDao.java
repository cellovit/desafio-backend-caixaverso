package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.entity.telemetry.TelemetryMetrics;
import br.gov.caixa.domain.repository.CustomEntityRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.response.cliente.ProdutoRecomendadoResponseDto;
import br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaQueryResultDto;
import br.gov.caixa.dto.response.telemetry.TelemetryServiceResponseDto;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@ApplicationScoped
@Slf4j
public class CustomEntityDao implements CustomEntityRepository {

    @Override
    public List<SimulacaoProdutoDiaQueryResultDto> simulacaoPorProdutoDia(PageParams pageParams) {
        var page = Page.of(pageParams.page() - 1, pageParams.pageSize());
        return find("SELECT new br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaQueryResultDto(s.produto, COUNT(s), s.dataSimulacao, AVG(s.valorFinal)) " +
                        "FROM Simulacao s " +
                        "GROUP BY s.produto, s.dataSimulacao ")
                .page(page)
                .list();
    }

//    @Override
    public List<TelemetryServiceResponseDto> filtrarDadosTelemetriaPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        Instant inicio = dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant fim = dataFim.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return find("SELECT new br.gov.caixa.dto.response.telemetry.TelemetryServiceResponseDto(t.endpoint, COUNT(t), AVG(t.tempoResposta)) as  " +
                        "FROM TelemetryMetrics t " +
                        "WHERE t.dataColeta BETWEEN :dataInicio AND :dataFim " +
                        "GROUP BY t.endpoint ",
                Parameters.with("dataInicio", inicio)
                        .and("dataFim", fim))
                .list();
    }
}
