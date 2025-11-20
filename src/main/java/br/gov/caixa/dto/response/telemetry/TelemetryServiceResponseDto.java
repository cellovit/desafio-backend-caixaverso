package br.gov.caixa.dto.response.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@RegisterForReflection
public class TelemetryServiceResponseDto implements Serializable {

    private String nome;
    private Long quantidadeChamadas;

    @JsonProperty("mediaTempoRespostaMs")
    private Double mediaTempoRespostaMs;

    /*
        O Hibernate necessita de um construtor com todos os parametros para popular o DTO a partir do resultado da query
    */
    public TelemetryServiceResponseDto(String nome, Long quantidadeChamadas, Double mediaTempoRespostaMs) {
        this.nome = nome;
        this.quantidadeChamadas = quantidadeChamadas;
        this.mediaTempoRespostaMs = mediaTempoRespostaMs;
    }

}
