package br.gov.caixa.exception;

import java.io.Serializable;

/**
 * Interface para acessar erros de negócio na aplicação.
 */

public interface BusinessErrorInterface extends Serializable {

    String getCode();
    String getTitle();
    String getDetailMessage();

}
