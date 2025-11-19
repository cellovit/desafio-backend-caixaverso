package br.gov.caixa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RiscoInvestimentoEnum {
    BAIXO("Baixo", 5.0),
    MEDIO("Médio", 10.0),
    ALTO("Alto", 15.0),
    MUITO_ALTO("Muito Alto", 20.0);

    private final String titulo;
    private final Double pontuacao;
}

