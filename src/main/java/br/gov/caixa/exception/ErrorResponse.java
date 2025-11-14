package br.gov.caixa.exception;

import java.io.Serializable;

public record ErrorResponse (

        String codigo,
        String titulo,
        String mensagem

) implements Serializable {
}
