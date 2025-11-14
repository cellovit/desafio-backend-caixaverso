package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public record ResultadoSimulacaoDto(
        @JsonProperty("valorFinal")
        BigDecimal valorFinal,

        @JsonProperty("rentabilidadeEfetiva")
        BigDecimal rentabilidadeEfetiva,

        @JsonProperty("prazoMeses")
        Integer prazoMeses
) implements Serializable {
}
