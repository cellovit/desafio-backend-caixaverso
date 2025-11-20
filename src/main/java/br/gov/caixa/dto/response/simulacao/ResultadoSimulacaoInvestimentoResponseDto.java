package br.gov.caixa.dto.response.simulacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ResultadoSimulacaoInvestimentoResponseDto(
        @JsonProperty("data")
        List<ResultadoSimulacaoProdutoDto> data,

        @JsonProperty("dataSimulacao")
        LocalDate dataSimulacao

) implements Serializable {
}
