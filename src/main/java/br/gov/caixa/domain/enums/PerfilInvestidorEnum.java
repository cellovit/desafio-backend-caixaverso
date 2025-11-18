package br.gov.caixa.domain.enums;

import br.gov.caixa.service.strategy.CdbStrategy;
import br.gov.caixa.service.strategy.FundoInvestimentoStrategy;
import br.gov.caixa.service.strategy.LciStrategy;
import br.gov.caixa.service.strategy.TesouroDiretoStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

import static br.gov.caixa.domain.constants.ApiConstants.*;

@Getter
@AllArgsConstructor
public enum PerfilInvestidorEnum {
    CONSERVADOR(TITULO_PERFIL_CONSERVADOR, "Baixa movimentação, foco em liquidez", PONTUACAO_PERFIL_CONSERVADOR),
    MODERADO(TITULO_PERFIL_MODERADO, "Equilíbrio entre liquidez e rentabilidade", PONTUACAO_PERFIL_MODERADO),
    AGRESSIVO(TITULO_PERFIL_AGRESSIVO, "Busca por alta rentabilidade, maior risco", PONTUACAO_PERFIL_AGRESSIVO);

    private final String nome;
    private final String descricao;
    private final BigDecimal pontuacao;

    public static PerfilInvestidorEnum fromNome(String nome) {
        for (PerfilInvestidorEnum perfil : PerfilInvestidorEnum.values()) {
            if (perfil.getNome().equalsIgnoreCase(nome)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Perfil de investidor inválido: " + nome);
    }

    public static PerfilInvestidorEnum fromPontuacao(BigDecimal pontuacao) {
        Optional<PerfilInvestidorEnum> perfil = Optional.of(CONSERVADOR);
        for (PerfilInvestidorEnum p : PerfilInvestidorEnum.values()) {
            if (pontuacao.compareTo(p.getPontuacao()) >= 0)  {
                perfil = Optional.of(p);
            }
        }
        return perfil.orElseThrow(() -> new IllegalArgumentException("Pontuação de investidor inválida: " + pontuacao));
    }
}
