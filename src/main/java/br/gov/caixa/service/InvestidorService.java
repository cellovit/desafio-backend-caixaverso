package br.gov.caixa.service;

import br.gov.caixa.dto.response.cliente.HistoricoInvestimentoResponseDto;
import br.gov.caixa.dto.response.cliente.PerfilRiscoResponseDto;

import java.util.List;

public interface InvestidorService {

    PerfilRiscoResponseDto findPerfilByClienteId(Long id);
    List<HistoricoInvestimentoResponseDto> getHistoricoInvestimentos(Long clienteId);
}
