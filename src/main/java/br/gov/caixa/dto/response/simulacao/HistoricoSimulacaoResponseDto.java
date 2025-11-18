package br.gov.caixa.dto.response.simulacao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record HistoricoSimulacaoResponseDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("clienteId")
        Long clienteId,

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

