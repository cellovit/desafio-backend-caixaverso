package br.gov.caixa.domain.enums;

public enum TipoProdutoEnum {
    CDB("Certificado de Depósito Bancário"),
    LCI("Letra de Crédito Imobiliário"),
    LCA("Letra de Crédito do Agronegócio"),
    CRI("Certificado de Recebíveis Imobiliários"),
    CRA("Certificado de Recebíveis do Agronegócio"),
    FII("Fundo de Investimento Imobiliário"),
    ETF("Exchange Traded Fund"),
    CRIPTOMOEDA("Criptomoeda"),
    DEBENTURE("Debenture"),
    POUPANCA("Poupança"),
    ACAO("Ação"),
    FUNDO_INVESTIMENTO("Fundo de Investimento");

    private final String descricao;

    TipoProdutoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
