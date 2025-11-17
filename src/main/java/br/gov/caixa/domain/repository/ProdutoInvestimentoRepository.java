package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface ProdutoInvestimentoRepository extends PanacheRepository<ProdutoInvestimento> {

    Optional<ProdutoInvestimento> findByTipo(String tipo);

}
