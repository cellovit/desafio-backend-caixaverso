package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDate;

public record SimulacaoProdutoDiaResponseDto(
        @JsonProperty("produto")
        String produto,

        @JsonProperty("data")
        LocalDate data,

        @JsonProperty("quantidadeSimulacoes")
        Integer quantidadeSimulacoes,

        @JsonProperty("mediaValorFinal")
        BigDecimal mediaValorFinal
) implements Serializable {
}

