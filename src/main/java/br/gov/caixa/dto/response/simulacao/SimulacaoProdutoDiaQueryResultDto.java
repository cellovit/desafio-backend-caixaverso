package br.gov.caixa.dto.response.simulacao;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@RegisterForReflection
public class SimulacaoProdutoDiaQueryResultDto {

    private String produto;

    private Long quantidadeSimulacoes;

    private LocalDate data;

    private Double mediaValorFinal;

    /*
        O HIbernate necessita de um construtor com todos os parametros para popular o DTO a partir do resultado da query
    */

    public SimulacaoProdutoDiaQueryResultDto(String produto, Long quantidadeSimulacoes, LocalDate data, Double mediaValorFinal) {
        this.produto = produto;
        this.quantidadeSimulacoes = quantidadeSimulacoes;
        this.data = data;
        this.mediaValorFinal = mediaValorFinal;
    }

}
