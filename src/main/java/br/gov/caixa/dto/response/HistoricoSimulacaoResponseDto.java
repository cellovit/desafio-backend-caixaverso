package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record HistoricoSimulacaoResponseDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("clienteId")
        Integer clienteId,

        @JsonProperty("produto")
        String produto,

        @JsonProperty("valorInvestido")
        BigDecimal valorInvestido,

        @JsonProperty("valorFinal")
        BigDecimal valorFinal,

        @JsonProperty("prazoMeses")
        Integer prazoMeses,

        @JsonProperty("dataSimulacao")
        ZonedDateTime dataSimulacao
) implements Serializable {
}

