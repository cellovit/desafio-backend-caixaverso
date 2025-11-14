package br.gov.caixa.domain.enums;

public enum PerfilInvestidorEnum {
    CONSERVADOR("Conservador", "Baixa movimentação, foco em liquidez"),
    MODERADO("Moderado", "Equilíbrio entre liquidez e rentabilidade"),
    AGRESSIVO("Agressivo", "Busca por alta rentabilidade, maior risco");

    private final String nome;
    private final String descricao;

    PerfilInvestidorEnum(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
