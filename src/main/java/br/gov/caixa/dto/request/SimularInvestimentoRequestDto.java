package br.gov.caixa.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public record SimularInvestimentoRequestDto(
        @JsonProperty("clienteId")
        Long clienteId,

        @JsonProperty("valor")
        BigDecimal valor,

        @JsonProperty("prazoMeses")
        Integer prazomeses,

        @NotNull
        @JsonProperty("tipoProduto")
        String tipoProduto
) implements Serializable {
}
