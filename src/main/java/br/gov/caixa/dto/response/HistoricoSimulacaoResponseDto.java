package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDate;

public record HistoricoSimulacaoResponseDto(
        @JsonProperty("id")
        BigInteger id,

        @JsonProperty("clienteId")
        BigInteger clienteId,

        @JsonProperty("produto")
        String produto,

        @JsonProperty("valorInvestido")
        BigDecimal valorInvestido,

        @JsonProperty("valorFinal")
        BigDecimal valorFinal,

        @JsonProperty("prazoMeses")
        Integer prazoMeses,

        @JsonProperty("dataSimulacao")
        LocalDate dataSimulacao
) implements Serializable {
}

