package br.gov.caixa.domain.enums;

public enum RiscoInvestimentoEnum {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private final String valor;

    RiscoInvestimentoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}

