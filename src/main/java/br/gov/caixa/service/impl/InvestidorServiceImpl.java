package br.gov.caixa.service.impl;

import br.gov.caixa.domain.entity.Investidor;
import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.dto.response.cliente.HistoricoInvestimentoResponseDto;
import br.gov.caixa.dto.response.cliente.PerfilRiscoResponseDto;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.mapper.InvestimentoMapper;
import br.gov.caixa.service.InvestidorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@ApplicationScoped
public class InvestidorServiceImpl implements InvestidorService {

    @Inject
    InvestidorRepository investidorRepository;

    @Inject
    InvestimentoMapper investimentoMapper;

    @Override
    public PerfilRiscoResponseDto findPerfilByClienteId(Long id) {
        log.info("Buscando perfil de risco do investidor pelo id do cliente: {}", id);
        var investidor = investidorRepository.findByIdOptional(id)
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.NOT_FOUND));
        return buildPerfilRiscoResponseDto(investidor);
    }

    // TODO: paginar investimentos
    @Override
    public List<HistoricoInvestimentoResponseDto> getHistoricoInvestimentos(Long clienteId) {
        log.info("Buscando histórico de investimentos do investidor pelo id do cliente: {}", clienteId);
        var investidor = investidorRepository.findByIdOptional(clienteId)
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.NOT_FOUND));

        return investidor.getInvestimentos().stream()
                .map(investimentoMapper::toHistoricoInvestimentoResponseDto)
                .toList();
    }

    private PerfilRiscoResponseDto buildPerfilRiscoResponseDto(Investidor investidor) {
        BigDecimal pontuacao = investidor.getPerfilInvestidor().getPontuacao();
        var perfilEnum = PerfilInvestidorEnum.fromLimiarPontuacao(pontuacao);

        return PerfilRiscoResponseDto.builder()
                .clienteId(investidor.getId())
                .perfilInvestidor(perfilEnum.getTitulo())
                .descricaoPerfilInvestidor(perfilEnum.getDescricao())
                .pontuacao(pontuacao)
                .build();
    }

}
