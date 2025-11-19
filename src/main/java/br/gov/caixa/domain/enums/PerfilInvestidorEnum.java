package br.gov.caixa.domain.enums;

import br.gov.caixa.exception.BusinessException;
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

    private final String titulo;
    private final String descricao;
    private final BigDecimal limiarPontuacao;

    public static PerfilInvestidorEnum fromNome(String nome) {
        for (PerfilInvestidorEnum perfil : PerfilInvestidorEnum.values()) {
            if (perfil.getTitulo().equalsIgnoreCase(nome)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Perfil de investidor inválido: " + nome);
    }

    public static PerfilInvestidorEnum fromLimiarPontuacao(BigDecimal pontuacao) {
        Optional<PerfilInvestidorEnum> perfil = Optional.of(CONSERVADOR);
        for (PerfilInvestidorEnum p : PerfilInvestidorEnum.values()) {
            if (pontuacao.compareTo(p.getLimiarPontuacao()) >= 0)  {
                perfil = Optional.of(p);
            }
        }
        // ("Pontuação de investidor inválida: " + pontuacao)
        return perfil.orElseThrow(() -> new BusinessException(BusinessExceptionEnum.ILLEGAL_ARGUMENT));
    }
}
