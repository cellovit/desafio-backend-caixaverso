-- =========================
-- PRODUTO_INVESTIMENTO
-- =========================
INSERT INTO dbo.produto_investimento (nome, tipo, rentabilidade, risco, created_at, updated_at, uuid)
VALUES
('CDB Banco XP', 'CDB', 0.0480, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('CDB Banco Itau', 'CDB', 0.0320, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('CDB Banco Bradesco', 'CDB', 0.0455, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('LCI Imobiliária', 'LCI', 0.0460, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('LCI Imobiliária', 'LCI', 0.0460, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('LCA Agronegócio', 'LCA', 0.0110, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('LCA Agronegócio', 'LCA', 0.0640, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('Debênture Infraestrutura', 'DEBENTURE', 0.0550, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('CRI Shopping', 'CRI', 0.0580, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('CRA Agro', 'CRA', 0.0570, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('FII HGLG11', 'FII', 0.0850, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('FII KNRI11', 'FII', 0.0820, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('FII MXRF11', 'FII', 0.0780, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('ETF BOVA11', 'ETF', 0.1020, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('ETF IVVB11', 'ETF', 0.1100, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('ETF SMAL11', 'ETF', 0.1250, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('Bitcoin', 'CRIPTOMOEDA', 0.2500, 'MUITO_ALTO', GETDATE(), GETDATE(), NEWID()),
('Ethereum', 'CRIPTOMOEDA', 0.2200, 'MUITO_ALTO', GETDATE(), GETDATE(), NEWID()),
('Solana', 'CRIPTOMOEDA', 0.1800, 'MUITO_ALTO', GETDATE(), GETDATE(), NEWID()),
('Cardano', 'CRIPTOMOEDA', 0.1600, 'MUITO_ALTO', GETDATE(), GETDATE(), NEWID()),
('Poupança Caixa', 'POUPANCA', 0.0300, 'BAIXO', GETDATE(), GETDATE(), NEWID()),
('Ações Petrobras PN', 'ACAO', 0.1200, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('Ações Vale ON', 'ACAO', 0.1150, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('Ações Itaú PN', 'ACAO', 0.0980, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('Fundo Tech Growth', 'FUNDO_INVESTIMENTO', 0.1400, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('Fundo Small Caps', 'FUNDO_INVESTIMENTO', 0.1350, 'ALTO', GETDATE(), GETDATE(), NEWID()),
('Fundo Multimercado Atlas', 'FUNDO_INVESTIMENTO', 0.0820, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('Fundo Multimercado Orion', 'FUNDO_INVESTIMENTO', 0.0910, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('Fundo Multimercado Vega', 'FUNDO_INVESTIMENTO', 0.0870, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('Debênture Corporativa', 'DEBENTURE', 0.0560, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('Debênture ESG', 'DEBENTURE', 0.0560, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('Debênture Financeira', 'DEBENTURE', 0.0565, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('CRI Corporativo', 'CRI', 0.0590, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('CRI Residencial', 'CRI', 0.0585, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('CRI Comercial', 'CRI', 0.0582, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('CRA Exportação', 'CRA', 0.0575, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('CRA Agroexport', 'CRA', 0.0578, 'MEDIO', GETDATE(), GETDATE(), NEWID()),
('TESOURO DIRETO', 'TESOURO', 0.0110, 'BAIXO', GETDATE(), GETDATE(), NEWID());


-- =========================
-- PERFIL_INVESTIDOR
-- =========================
INSERT INTO dbo.perfil_investidor (titulo, pontuacao, created_at, updated_at, uuid)
VALUES
('CONSERVADOR', 10.00, GETDATE(), GETDATE(), NEWID()),
('CONSERVADOR', 15.00, GETDATE(), GETDATE(), NEWID()),
('MODERADO', 57.00, GETDATE(), GETDATE(), NEWID()),
('AGRESSIVO', 83.00, GETDATE(), GETDATE(), NEWID()),
('AGRESSIVO', 95.00, GETDATE(), GETDATE(), NEWID());

-- =========================
-- INVESTIDOR
-- =========================
INSERT INTO dbo.investidor (nome, created_at, updated_at, uuid, perfil_id)
VALUES
('João Silva', GETDATE(), GETDATE(), NEWID(), 1),
('Maria Souza', GETDATE(), GETDATE(), NEWID(), 2),
('Carlos Pereira', GETDATE(), GETDATE(), NEWID(), 3),
('Ana Costa', GETDATE(), GETDATE(), NEWID(), 4),
('Pedro Santos', GETDATE(), GETDATE(), NEWID(), 5);

INSERT INTO dbo.investidor (nome, created_at, updated_at, uuid)
VALUES
('Marcelo Vitório', GETDATE(), GETDATE(), NEWID()),
('Isabela da Silva', GETDATE(), GETDATE(), NEWID());

-- =========================
-- INVESTIMENTO
-- =========================

INSERT INTO dbo.investimento (titulo, tipo, valor_aplicado, prazo_meses, rentabilidade, data_aplicacao, created_at, updated_at, uuid, investidor_id, risco)
VALUES
-- Investidor 1
('CDB Banco XP', 'CDB', 1500.00, 18, 0.0480, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'BAIXO'),
('LCI Imobiliária', 'LCI', 3000.00, 36, 0.0460, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'BAIXO'),
('LCA Agronegócio', 'LCA', 2500.00, 24, 0.0470, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'BAIXO'),
('Debênture Infraestrutura', 'DEBENTURE', 4000.00, 48, 0.0550, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'MEDIO'),
('CRI Shopping', 'CRI', 2800.00, 24, 0.0580, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'MEDIO'),
('CRA Agro', 'CRA', 3500.00, 36, 0.0570, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'MEDIO'),
('FII HGLG11', 'FII', 2500.00, 24, 0.0850, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'MEDIO'),
('ETF BOVA11', 'ETF', 4000.00, 48, 0.1020, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'ALTO'),
('Bitcoin', 'CRIPTOMOEDA', 5000.00, 12, 0.2500, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'MUITO_ALTO'),
('Ações Petrobras PN', 'ACAO', 3000.00, 36, 0.1200, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1, 'ALTO'),

-- Investidor 2
('CDB Banco Inter', 'CDB', 2000.00, 24, 0.0490, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'BAIXO'),
('LCI Banco Bradesco', 'LCI', 2500.00, 24, 0.0465, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'BAIXO'),
('LCA Banco Santander', 'LCA', 2700.00, 24, 0.0475, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'BAIXO'),
('Debênture Corporativa', 'DEBENTURE', 3200.00, 36, 0.0560, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'MEDIO'),
('CRI Corporativo', 'CRI', 3000.00, 24, 0.0590, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'MEDIO'),
('CRA Exportação', 'CRA', 2800.00, 24, 0.0575, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'MEDIO'),
('FII KNRI11', 'FII', 2600.00, 24, 0.0820, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'MEDIO'),
('ETF IVVB11', 'ETF', 4200.00, 48, 0.1100, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'ALTO'),
('Ethereum', 'CRIPTOMOEDA', 4000.00, 12, 0.2200, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'MUITO_ALTO'),
('Ações Vale ON', 'ACAO', 2800.00, 36, 0.1150, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2, 'ALTO'),

-- Investidor 3
('CDB Banco Original', 'CDB', 2200.00, 24, 0.0485, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'BAIXO'),
('LCI Banco Itaú', 'LCI', 2600.00, 24, 0.0468, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'BAIXO'),
('LCA Banco do Brasil', 'LCA', 2400.00, 24, 0.0472, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'BAIXO'),
('Debênture ESG', 'DEBENTURE', 3200.00, 36, 0.0560, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'MEDIO'),
('CRI Residencial', 'CRI', 2800.00, 24, 0.0585, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'MEDIO'),
('CRA Agroexport', 'CRA', 3100.00, 36, 0.0578, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'MEDIO'),
('FII MXRF11', 'FII', 2400.00, 24, 0.0780, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'MEDIO'),
('ETF SMAL11', 'ETF', 3800.00, 36, 0.1250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'ALTO'),
('Solana', 'CRIPTOMOEDA', 3000.00, 12, 0.1800, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'MUITO_ALTO'),
('Ações Itaú PN', 'ACAO', 2500.00, 24, 0.0980, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3, 'ALTO'),

-- Investidor 4
('CDB Banco BTG', 'CDB', 2100.00, 24, 0.0482, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'BAIXO'),
('LCI Banco Safra', 'LCI', 2700.00, 24, 0.0469, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'BAIXO'),
('LCA Banco Pan', 'LCA', 2300.00, 24, 0.0473, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'BAIXO'),
('Debênture Financeira', 'DEBENTURE', 3500.00, 36, 0.0565, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'MEDIO'),
('CRI Comercial', 'CRI', 2900.00, 24, 0.0582, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'MEDIO'),
('CRA Industrial', 'CRA', 3200.00, 36, 0.0579, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'MEDIO'),
('FII XPML11', 'FII', 2700.00, 24, 0.0830, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'MEDIO'),
('ETF ECOO11', 'ETF', 4100.00, 48, 0.1090, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'ALTO'),
('Cardano', 'CRIPTOMOEDA', 2500.00, 12, 0.1600, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'MUITO_ALTO'),
('Poupança Caixa', 'POUPANCA', 2000.00, 12, 0.0300, GETDATE(), GETDATE(), GETDATE(), NEWID(), 4, 'BAIXO'),

-- Investidor 5
('CDB Banco Neon', 'CDB', 1900.00, 18, 0.0481, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'BAIXO'),
('LCI Banco BV', 'LCI', 2800.00, 24, 0.0467, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'BAIXO'),
('ETF SMAL11', 'ETF', 3800.00, 36, 0.1250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'ALTO'),
('ETF SMAL12', 'ETF', 3800.00, 36, 0.1250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'ALTO'),
('ETF SMAL13', 'ETF', 3800.00, 36, 0.1250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'ALTO'),
('Cardano', 'CRIPTOMOEDA', 2500.00, 12, 0.1600, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'MUITO_ALTO'),
('Bitcoin', 'CRIPTOMOEDA', 2500.00, 12, 0.1600, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'MUITO_ALTO'),
('Ethereum', 'CRIPTOMOEDA', 2500.00, 12, 0.1600, GETDATE(), GETDATE(), GETDATE(), NEWID(), 5, 'MUITO_ALTO'),

-- Investidor 7
('ETF SMAL11', 'ETF', 3800.00, 36, 0.1250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 7, 'ALTO'),
('ETF SMAL12', 'ETF', 3800.00, 36, 0.1250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 7, 'ALTO'),
('ETF SMAL13', 'ETF', 3800.00, 36, 0.1250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 7, 'ALTO'),
('Cardano', 'CRIPTOMOEDA', 2500.00, 12, 0.1600, GETDATE(), GETDATE(), GETDATE(), NEWID(), 7, 'MUITO_ALTO'),
('Bitcoin', 'CRIPTOMOEDA', 2500.00, 12, 0.1600, GETDATE(), GETDATE(), GETDATE(), NEWID(), 7, 'MUITO_ALTO'),
('Ethereum', 'CRIPTOMOEDA', 2500.00, 12, 0.1600, GETDATE(), GETDATE(), GETDATE(), NEWID(), 7, 'MUITO_ALTO');