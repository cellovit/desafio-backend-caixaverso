package br.gov.caixa.service.impl;

import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.entity.ProdutoInvestimento;
import br.gov.caixa.domain.enums.BusinessExceptionEnum;
import br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.enums.RiscoInvestimentoEnum;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.domain.repository.PerfilInvestidorRepository;
import br.gov.caixa.exception.BusinessException;
import br.gov.caixa.service.MotorRecomendacaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class MotorRecomendacaoServiceImpl implements MotorRecomendacaoService {

    @Inject
    InvestidorRepository investidorRepository;

    @Inject
    PerfilInvestidorRepository perfilInvestidorRepository;

    private List<ProdutoInvestimento> buscarProdutosRecomendados(Long investidorId) {

        log.info("Buscando investidor com o id: {}", investidorId);
        var investidor = investidorRepository.findByIdOptional(investidorId)
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.INVESTIDOR_NAO_ENCONTRADO));

        var investimentos = investidor.getInvestimentos();

        log.info("Calculando tipo mais comum de investimento do investidor id: {}", investidorId);
        var tipoMaisComumInvestimento = calculaIncidenciaTipoInvestimento(investimentos);

        log.info("Calculando pontuação por tipo de investimento do investidor id: {}", investidorId);
        var pontuacaoMediaPorInvestimento = calculaPontuacaoPorTipoinvestimento(investimentos);

        log.info("Calculando foco entre liquidez/rentabilidade do investidor id: {}", investidorId);
        var foco = calculaFoco(investimentos);

        var pontuacaoFinal = foco.getPontuacao()
                + pontuacaoMediaPorInvestimento
                + tipoMaisComumInvestimento.getPontuacao();
        log.debug("Pontuação final do investidor {} : {}", investidorId, pontuacaoFinal);

        var perfilEnum = PerfilInvestidorEnum.fromLimiarPontuacao(BigDecimal.valueOf(pontuacaoFinal));
        log.debug("Perfil definido para o investidor {} : {}", investidorId, perfilEnum.getTitulo().toString());

        var perfil = perfilInvestidorRepository.buscarPorPerfilPorTitulo(perfilEnum.getTitulo())
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.PERFIL_INVESTIDOR_NAO_ENCONTRADO));

        log.info("Retornando produtos recomendados do investidor id: {}", investidorId);
        log.debug("Produtos Recomendados: {}", perfil.getProdutosRecomendados());
        return perfil.getProdutosRecomendados();
    }

    private FocoProdutoInvestimentoEnum calculaFoco(List<Investimento> investimentos) {
        Map<FocoProdutoInvestimentoEnum, Long> contagem = investimentos.stream()
                .collect(Collectors.groupingBy((in) -> in.getTipo().getFoco(), Collectors.counting()));

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(FocoProdutoInvestimentoEnum.LIQUIDEZ);
    }

    private Double calculaPontuacaoPorTipoinvestimento(List<Investimento> investimentos) {
        return investimentos.stream()
                .map(inv -> inv.getTipo().getPontuacao())
                .reduce(0, Integer::sum)
                / (double) investimentos.size();
    }

    private RiscoInvestimentoEnum calculaIncidenciaTipoInvestimento(List<Investimento> investimentos) {
        log.debug("Agrupando investimentos por risco e conta ocorrências");
        Map<RiscoInvestimentoEnum, Long> contagem = investimentos.stream()
                .collect(Collectors.groupingBy(Investimento::getRisco, Collectors.counting()));

        log.debug("Encontrando o risco de maior incidência");
        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(RiscoInvestimentoEnum.BAIXO);
    }

}
