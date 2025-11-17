package br.gov.caixa.domain.enums;

import br.gov.caixa.exception.BusinessErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessExceptionEnum implements BusinessErrorInterface {

    NOT_FOUND("404", "Not Found", "Recurso não encontrado"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error", "Erro interno do servidor"),
    UNAUTHORIZED("401", "Unauthorized", "Acesso não autorizado"),
    BAD_REQUEST("400", "Bad Request", "Requisição inválida"),
    TIMEOUT("524", "Request Timeout", "Tempo de requisição esgotado"),
    ILLEGAL_ARGUMENT("400", "Illegal Argument", "Argumento ilegal fornecido"),
    UNPROCESSABLE_ENTITY("422", "Unprocessable Entity", "Entidade não processável"),
    PRODUTO_INVESTIMENTO_FORA_DO_ESCOPO("422", "Produto Investimento Fora do Escopo", "O produto de investimento solicitado não pode ser processado neste contexto."),
    INVESTIDOR_NAO_ENCONTRADO("404", "Investidor Não Encontrado", "O investidor especificado não foi encontrado na base de dados.");

    private final String code;
    private final String title;
    private final String detailMessage;

}
