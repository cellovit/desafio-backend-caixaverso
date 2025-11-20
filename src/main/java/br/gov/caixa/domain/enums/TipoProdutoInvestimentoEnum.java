package br.gov.caixa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum.LIQUIDEZ;
import static br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum.RENTABILIDADE;

@Getter
@AllArgsConstructor
@Slf4j
public enum TipoProdutoInvestimentoEnum {
    CDB("Certificado de Depósito Bancário", 10, LIQUIDEZ),
    TESOURO("Tesouro Direto", 7, LIQUIDEZ),
    LCI("Letra de Crédito Imobiliário", 12, RENTABILIDADE),
    LCA("Letra de Crédito do Agronegócio", 12, RENTABILIDADE),
    CRI("Certificado de Recebíveis Imobiliários", 15, RENTABILIDADE),
    CRA("Certificado de Recebíveis do Agronegócio", 15, RENTABILIDADE),
    FII("Fundo de Investimento Imobiliário", 20, RENTABILIDADE),
    ETF("Exchange Traded Fund", 25, LIQUIDEZ),
    CRIPTOMOEDA("Criptomoeda", 50, RENTABILIDADE),
    DEBENTURE("Debenture", 15, RENTABILIDADE),
    POUPANCA("Poupança", 5, LIQUIDEZ),
    ACAO("Ação", 40, RENTABILIDADE),
    FUNDO_INVESTIMENTO("Fundo de Investimento", 20, RENTABILIDADE);

    private final String descricao;
    private final int pontuacao;
    private final FocoProdutoInvestimentoEnum foco;

    public static TipoProdutoInvestimentoEnum fromTitulo(String titulo) {
        log.info("titulo1: {}", titulo);
        for (TipoProdutoInvestimentoEnum tipo : TipoProdutoInvestimentoEnum.values()) {
            log.info("TIPO1: {}", tipo.toString());
            if (tipo.toString().equalsIgnoreCase(titulo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de produto de investimento inválido: " + titulo);
    }

}
