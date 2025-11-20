package br.gov.caixa.dto.response.simulacao;

import br.gov.caixa.dto.response.cliente.ProdutoRecomendadoResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record ResultadoSimulacaoProdutoDto(
        @JsonProperty("produtoValidado")
        ProdutoRecomendadoResponseDto produtoValidado,

        @JsonProperty("resultadoSimulacaoDto")
        ResultadoSimulacaoDto resultadoSimulacaoDto
) implements Serializable {
}
