package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.dto.PageParams;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class ProdutoInvestimentoDao implements ProdutoInvestimentoRepository {

    @Override
    public Optional<ProdutoInvestimento> findByTipo(String tipo) {
        log.info("buscando produto de investimento pelo tipo: {}", tipo);
        return find("tipo", tipo).stream().findFirst();
    }

    @Override
    public List<ProdutoInvestimento> findByPerfil(String tituloPerfil, PageParams pageParams) {
        var page = Page.of(pageParams.page() - 1, pageParams.pageSize());
        return find("SELECT p " +
                "FROM ProdutoInvestimento p " +
                "JOIN p.perfis pi " +
                "WHERE pi.titulo = :tituloPerfil ", Parameters.with("tituloPerfil", tituloPerfil))
                .page(page)
                .list();
    }

}
