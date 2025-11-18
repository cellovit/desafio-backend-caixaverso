package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.dto.PageParams;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoInvestimentoRepository extends PanacheRepository<ProdutoInvestimento> {

    Optional<ProdutoInvestimento> findByTipo(String tipo);
    List<ProdutoInvestimento> findByPerfil(String tituloPerfil, PageParams pageParams);

}
