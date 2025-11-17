package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface ProdutoRepository extends PanacheRepository<Produto> {
}
