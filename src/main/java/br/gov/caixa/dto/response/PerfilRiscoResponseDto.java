package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record PerfilRiscoResponseDto(
        @JsonProperty("clienteId")
        Long clienteId,

        @JsonProperty("perfil")
        String perfilInvestidor,

        @JsonProperty("pontuacao")
        BigDecimal pontuacao,

        @JsonProperty("descricao")
        String descricaoPerfilInvestidor
) implements Serializable {
}
