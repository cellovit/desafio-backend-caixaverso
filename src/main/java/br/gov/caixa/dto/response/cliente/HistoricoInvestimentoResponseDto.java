package br.gov.caixa.dto.response.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
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
