package br.gov.caixa.dto.response.simulacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@RegisterForReflection
public record SimulacaoProdutoDiaResponseDto(
        @JsonProperty("produto")
        String produto,

        @JsonProperty("quantidadeSimulacoes")
        Integer quantidadeSimulacoes,

        @JsonProperty("data")
        LocalDate data,

        @JsonProperty("mediaValorFinal")
        BigDecimal mediaValorFinal
) implements Serializable {
}

