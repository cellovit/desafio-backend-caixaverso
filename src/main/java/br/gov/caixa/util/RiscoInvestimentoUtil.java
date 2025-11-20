package br.gov.caixa.util;

import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.enums.RiscoInvestimentoEnum;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
@Slf4j
public class RiscoInvestimentoUtil {
    public static List<RiscoInvestimentoEnum> defineRiscoPorPerfil(PerfilInvestidorEnum perfil) {
        return switch (perfil) {
            case CONSERVADOR -> List.of(RiscoInvestimentoEnum.BAIXO);
            case MODERADO -> List.of(RiscoInvestimentoEnum.MEDIO);
            case AGRESSIVO -> List.of(RiscoInvestimentoEnum.ALTO, RiscoInvestimentoEnum.MUITO_ALTO);
        };
    }

    public static FocoProdutoInvestimentoEnum calculaFoco(List<Investimento> investimentos) {
        Map<FocoProdutoInvestimentoEnum, Long> contagem = investimentos.stream()
                .collect(Collectors.groupingBy((in) -> in.getTipo().getFoco(), Collectors.counting()));

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(FocoProdutoInvestimentoEnum.LIQUIDEZ);
    }

    public static Double calculaPontuacaoPorTipoinvestimento(List<Investimento> investimentos) {
        return investimentos.stream()
                .map(inv -> inv.getTipo().getPontuacao())
                .reduce(0, Integer::sum)
                / (double) investimentos.size();
    }

    public static RiscoInvestimentoEnum calculaIncidenciaTipoInvestimento(List<Investimento> investimentos) {
        log.debug("Agrupando investimentos por risco e conta ocorrências");
        Map<RiscoInvestimentoEnum, Long> contagem = investimentos.stream()
                .collect(Collectors.groupingBy(Investimento::getRisco, Collectors.counting()));

        log.debug("Encontrando o risco de maior incidência");
        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(RiscoInvestimentoEnum.BAIXO);
    }

    public static Double calculaPontuacaoFinalInvestidor(List<Investimento> investimentos) {
        return calculaFoco(investimentos).getPontuacao()
                + calculaPontuacaoPorTipoinvestimento(investimentos)
                + calculaIncidenciaTipoInvestimento(investimentos).getPontuacao();
    }

    public static Double calculaPontuacaoFinalInvestidor(Double pontuacaoMediaPorInvestimento, FocoProdutoInvestimentoEnum foco, RiscoInvestimentoEnum riscoInvestimentoEnum) {
        return pontuacaoMediaPorInvestimento + foco.getPontuacao() + riscoInvestimentoEnum.getPontuacao();
    }
}
