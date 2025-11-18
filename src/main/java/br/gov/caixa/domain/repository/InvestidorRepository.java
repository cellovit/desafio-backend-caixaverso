package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.Investidor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface InvestidorRepository extends PanacheRepository<Investidor> {
}
