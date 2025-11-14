package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record HistoricoInvestimentoResponseDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("tipo")
        String tipo,

        @JsonProperty("valor")
        BigDecimal valor,

        @JsonProperty("rentabilidade")
        BigDecimal rentabilidade,

        @JsonProperty("data")
        LocalDate data
) implements Serializable {
}
