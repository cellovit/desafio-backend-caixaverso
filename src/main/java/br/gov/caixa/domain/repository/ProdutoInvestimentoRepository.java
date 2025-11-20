package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.enums.RiscoInvestimentoEnum;
import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import br.gov.caixa.dto.PageParams;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoInvestimentoRepository extends PanacheRepository<ProdutoInvestimento> {

    Optional<ProdutoInvestimento> findFirstByTipo(TipoProdutoInvestimentoEnum tipo);
    List<ProdutoInvestimento> findByRisco(RiscoInvestimentoEnum risco);
//    List<ProdutoInvestimento> findByPerfil(String tituloPerfil, PageParams pageParams);
    List<ProdutoInvestimento> findByRisco(RiscoInvestimentoEnum risco, PageParams pageParams);

}
