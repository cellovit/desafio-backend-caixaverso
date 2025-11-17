package br.gov.caixa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static br.gov.caixa.domain.constants.ApiConstants.*;

@Getter
@AllArgsConstructor
public enum PerfilInvestidorEnum {
    CONSERVADOR(TITULO_PERFIL_CONSERVADOR, "Baixa movimentação, foco em liquidez", PONTUACAO_PERFIL_CONSERVADOR),
    MODERADO(TITULO_PERFIL_MODERADO, "Equilíbrio entre liquidez e rentabilidade", PONTUACAO_PERFIL_MODERADO),
    AGRESSIVO(TITULO_PERFIL_AGRESSIVO, "Busca por alta rentabilidade, maior risco", PONTUACAO_PERFIL_AGRESSIVO);

    private final String nome;
    private final String descricao;
    private final Double pontuacao;
}
