package br.gov.caixa.dto.response.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
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
