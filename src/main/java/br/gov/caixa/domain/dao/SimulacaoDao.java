package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.Simulacao;
import br.gov.caixa.domain.repository.SimulacaoRepository;
import br.gov.caixa.dto.PageParams;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApplicationScoped
@Slf4j
public class SimulacaoDao implements SimulacaoRepository {

    @Override
    public List<Simulacao> findPaginado(PageParams pageParams) {
        var page = Page.of(pageParams.page() - 1, pageParams.pageSize());
        return Simulacao.findAll()
                .page(page)
                .list();
    }

}
