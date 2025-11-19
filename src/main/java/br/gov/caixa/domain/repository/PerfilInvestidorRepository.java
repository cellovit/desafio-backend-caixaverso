package br.gov.caixa.domain.repository;

import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.entity.PerfilInvestidor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface PerfilInvestidorRepository extends PanacheRepository<PerfilInvestidor> {

    Optional<PerfilInvestidor> buscarPorPerfilPorTitulo(String titulo);

}
