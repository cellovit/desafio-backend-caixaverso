package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@ApplicationScoped
@Slf4j
public class ProdutoInvestimentoDao implements ProdutoInvestimentoRepository {

    @Override
    public Optional<ProdutoInvestimento> findByTipo(String tipo) {
        log.info("buscando produto de investimento pelo tipo: {}", tipo);
        return find("tipo", tipo).stream().findFirst();
    }

}
