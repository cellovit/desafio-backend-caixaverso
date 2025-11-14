package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record PerfilRiscoResponseDto(
        @JsonProperty("clienteId")
        Long clienteId,

        @JsonProperty("perfil")
        String perfilInvestidor,

        @JsonProperty("pontuacao")
        Integer pontuacao,

        @JsonProperty("descricao")
        String descricaoPerfilInvestidor
) implements Serializable {
}
