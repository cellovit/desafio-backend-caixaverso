package br.gov.caixa.service.impl;

import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.enums.RiscoInvestimentoEnum;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.domain.repository.PerfilInvestidorRepository;
import br.gov.caixa.domain.repository.ProdutoInvestimentoRepository;
import br.gov.caixa.dto.PageParams;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.service.MotorRecomendacaoService;
import br.gov.caixa.util.RiscoInvestimentoUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.gov.caixa.util.RiscoInvestimentoUtil.*;

@ApplicationScoped
@Slf4j
public class MotorRecomendacaoServiceImpl implements MotorRecomendacaoService {

    @Inject
    InvestidorRepository investidorRepository;

    @Inject
    ProdutoInvestimentoRepository produtoInvestimentoRepository;

    @Override
    public PerfilInvestidorEnum calcularPerfil(Long investidorId) {

        log.info("Buscando investidor com o id: {}", investidorId);
        var investidor = investidorRepository.findByIdOptional(investidorId)
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.INVESTIDOR_NAO_ENCONTRADO));

        var investimentos = investidor.getInvestimentos();

        log.info("Calculando tipo mais comum de investimento do investidor id: {}", investidorId);
        var tipoMaisComumInvestimento = calculaIncidenciaTipoInvestimento(investimentos);

        Double pontuacaoMediaPorInvestimento = 0.0;
        FocoProdutoInvestimentoEnum foco = FocoProdutoInvestimentoEnum.LIQUIDEZ;

        if (!investimentos.isEmpty()) {
            log.info("Calculando pontuação por tipo de investimento do investidor id: {}", investidorId);
            pontuacaoMediaPorInvestimento = calculaPontuacaoPorTipoinvestimento(investimentos);

            log.info("Calculando foco entre liquidez/rentabilidade do investidor id: {}", investidorId);
            foco = calculaFoco(investimentos);
        }

        var pontuacaoFinal = calculaPontuacaoFinalInvestidor(pontuacaoMediaPorInvestimento, foco, tipoMaisComumInvestimento);
        log.debug("Pontuação final do investidor {} : {}", investidorId, pontuacaoFinal);

        var perfilEnum = PerfilInvestidorEnum.fromLimiarPontuacao(BigDecimal.valueOf(pontuacaoFinal));
        log.debug("Perfil definido para o investidor {} : {}", investidorId, perfilEnum.getTitulo().toString());
        return perfilEnum;
    }


}
