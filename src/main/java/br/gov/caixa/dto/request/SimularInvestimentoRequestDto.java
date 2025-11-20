package br.gov.caixa.dto.request;

import br.gov.caixa.domain.enums.TipoProdutoInvestimentoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public record SimularInvestimentoRequestDto(
        @NotNull
        @JsonProperty("clienteId")
        Long clienteId,

        @NotNull
        @JsonProperty("valor")
        BigDecimal valor,

        @NotNull
        @JsonProperty("prazoMeses")
        Integer prazomeses,

        @JsonProperty("tipoProduto")
        String tipoProduto
) implements Serializable {
}
