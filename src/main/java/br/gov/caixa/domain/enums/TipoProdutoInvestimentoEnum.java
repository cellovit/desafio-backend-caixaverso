package br.gov.caixa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum.LIQUIDEZ;
import static br.gov.caixa.domain.enums.FocoProdutoInvestimentoEnum.RENTABILIDADE;

@Getter
@AllArgsConstructor
public enum TipoProdutoInvestimentoEnum {
    CDB("Certificado de Depósito Bancário", 10, LIQUIDEZ),
    LCI("Letra de Crédito Imobiliário", 12, RENTABILIDADE),
    LCA("Letra de Crédito do Agronegócio", 12, RENTABILIDADE),
    CRI("Certificado de Recebíveis Imobiliários", 18, RENTABILIDADE),
    CRA("Certificado de Recebíveis do Agronegócio", 18, RENTABILIDADE),
    FII("Fundo de Investimento Imobiliário", 20, RENTABILIDADE),
    ETF("Exchange Traded Fund", 25, LIQUIDEZ),
    CRIPTOMOEDA("Criptomoeda", 40, RENTABILIDADE),
    DEBENTURE("Debenture", 15, RENTABILIDADE),
    POUPANCA("Poupança", 5, LIQUIDEZ),
    ACAO("Ação", 30, RENTABILIDADE),
    FUNDO_INVESTIMENTO("Fundo de Investimento", 20, RENTABILIDADE);

    private final String descricao;
    private final int pontuacao;
    private final FocoProdutoInvestimentoEnum foco;
}
