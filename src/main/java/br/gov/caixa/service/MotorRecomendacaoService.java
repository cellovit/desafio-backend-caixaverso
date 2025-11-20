package br.gov.caixa.service;

import br.gov.caixa.domain.enums.PerfilInvestidorEnum;

public interface MotorRecomendacaoService {

    PerfilInvestidorEnum calcularPerfil(Long investidorId);

}
