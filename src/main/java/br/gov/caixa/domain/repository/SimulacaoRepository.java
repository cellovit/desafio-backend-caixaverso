package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.Simulacao;
import br.gov.caixa.dto.PageParams;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.time.LocalDate;
import java.util.List;

public interface SimulacaoRepository extends PanacheRepository<Simulacao> {

    List<Simulacao> findPaginado(PageParams pageParams);
    List<Simulacao> findPaginadoPorProdutoDia(String produto, LocalDate dataInicio, LocalDate dataFim, PageParams pageParams);

}
