package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface InvestimentoRepository extends PanacheRepository<Investimento> {
}
