package br.gov.caixa.exception.response;

import java.io.Serializable;

public record ErrorResponse (

        String codigo,
        String titulo,
        String mensagem

) implements Serializable {
}
