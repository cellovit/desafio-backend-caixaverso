package br.gov.caixa.domain.dao;

import br.gov.caixa.domain.entity.Produto;
import br.gov.caixa.domain.repository.ProdutoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProdutoDao implements ProdutoRepository {

    public Optional<Produto> findByNome(String nome) {
        return find("nome", nome).stream().findFirst();
    }

}
