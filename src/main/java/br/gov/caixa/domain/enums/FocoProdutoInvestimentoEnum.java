package br.gov.caixa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FocoProdutoInvestimentoEnum {

    LIQUIDEZ(10.0),
    RENTABILIDADE(25.0);

    private final Double pontuacao;
}
