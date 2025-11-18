package br.gov.caixa.service.impl;

import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.service.MotorRecomendacaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class MotorRecomendacaoServiceImpl implements MotorRecomendacaoService {

    @Inject
    InvestidorRepository investidorRepository;

    public void teste() {

    }

    private void verificarinvestimentos() {

    }

    private String verificarPreferencia() {
        return "";
    }

}
