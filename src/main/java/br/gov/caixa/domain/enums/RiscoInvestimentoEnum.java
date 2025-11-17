package br.gov.caixa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RiscoInvestimentoEnum {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private final String titulo;
}

