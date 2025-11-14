package br.gov.caixa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record ResultadoSimulacaoInvestimentoResponseDto(
        @JsonProperty("produtoValidado")
        ProdutoRecomendadoResponseDto produtoValidado,

        @JsonProperty("resultadoSimulacaoDto")
        ResultadoSimulacaoDto resultadoSimulacaoDto,

        @JsonProperty("dataSimulacao")
        ZonedDateTime dataSimulacao
) implements Serializable {
}
