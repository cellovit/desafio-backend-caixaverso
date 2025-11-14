package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProdutoRecomendadoResponseDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("nome")
        String nome,

        @JsonProperty("tipo")
        String tipo,

        @JsonProperty("rentabilidade")
        BigDecimal rentabilidade,

        @JsonProperty("risco")
        String risco
) implements Serializable {
}
