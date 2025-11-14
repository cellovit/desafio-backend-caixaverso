package br.gov.caixa.repository;

import br.gov.caixa.domain.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public Optional<Produto> findByNome(String nome) {
        return find("nome", nome).stream().findFirst();
    }

}
