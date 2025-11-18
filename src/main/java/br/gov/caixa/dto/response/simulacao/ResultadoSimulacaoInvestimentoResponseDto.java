package br.gov.caixa.dto.response.simulacao;

import br.gov.caixa.dto.response.ProdutoRecomendadoResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record ResultadoSimulacaoInvestimentoResponseDto(
        @JsonProperty("produtoValidado")
        ProdutoRecomendadoResponseDto produtoValidado,

        @JsonProperty("resultadoSimulacaoDto")
        ResultadoSimulacaoDto resultadoSimulacaoDto,

        @JsonProperty("dataSimulacao")
        LocalDate dataSimulacao
) implements Serializable {
}
