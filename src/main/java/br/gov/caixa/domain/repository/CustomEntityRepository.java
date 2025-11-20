package br.gov.caixa.domain.repository;

import br.gov.caixa.dto.PageParams;
import br.gov.caixa.dto.response.simulacao.SimulacaoProdutoDiaQueryResultDto;
import br.gov.caixa.dto.response.telemetry.TelemetryServiceResponseDto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.time.LocalDate;
import java.util.List;

/*
    interface criada para evitar o bug do panacheRepository que força a conversão dos objetos
    retornados pelas querys para a entidade definida no generics da interface
*/
public interface CustomEntityRepository extends PanacheRepository {
    List<SimulacaoProdutoDiaQueryResultDto> simulacaoPorProdutoDia(PageParams pageParams);
    List<TelemetryServiceResponseDto> filtrarDadosTelemetriaPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
}
