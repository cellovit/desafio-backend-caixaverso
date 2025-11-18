package br.gov.caixa.service;

import br.gov.caixa.dto.response.PerfilRiscoResponseDto;

public interface InvestidorService {

    PerfilRiscoResponseDto findPerfilByClienteId(Long id);
}
