-- Inserindo perfis de investidor
INSERT INTO perfil_investidor (pontuacao, titulo, created_at, updated_at, uuid)
VALUES
(23.0, 'Conservador', GETDATE(), GETDATE(), NEWID()),
(58.0, 'Moderado', GETDATE(), GETDATE(), NEWID()),
(90.0, 'Agressivo', GETDATE(), GETDATE(), NEWID());

-- Inserindo investidores
INSERT INTO investidor (nome, created_at, updated_at, uuid, perfil_id)
VALUES
('João Silva', GETDATE(), GETDATE(), NEWID(), 1),
('Maria Souza', GETDATE(), GETDATE(), NEWID(), 2),
('Carlos Pereira', GETDATE(), GETDATE(), NEWID(), 3);

-- Inserindo produtos de investimento
INSERT INTO produto_investimento (nome, tipo, rentabilidade, risco, created_at, updated_at, uuid)
VALUES
('CDB Banco X', 'CDB', 0.0125, 'Baixo', GETDATE(), GETDATE(), NEWID()),
('Tesouro Selic', 'TESOURO', 0.0100, 'Baixo', GETDATE(), GETDATE(), NEWID()),
('Fundo Agressivo Y', 'FUNDO', 0.0250, 'Alto', GETDATE(), GETDATE(), NEWID()),
('LCI Banco Z', 'LCI', 0.0110, 'Baixo', GETDATE(), GETDATE(), NEWID()),
('Fundo Moderado W', 'FUNDO', 0.0180, 'Médio', GETDATE(), GETDATE(), NEWID()),
('LCA Banco Z', 'LCA', 0.0200, 'Baixo', GETDATE(), GETDATE(), NEWID()),
('Ação de Empresa nova', 'ACAO', 0.2500, 'Alto', GETDATE(), GETDATE(), NEWID()),
('Ação de Empresa consolidada', 'ACAO', 0.1500, 'Alto', GETDATE(), GETDATE(), NEWID());

-- Inserindo relação produto-perfil (N:N)
INSERT INTO produto_perfil (produto_id, perfil_id)
VALUES
(1, 1), -- CDB Banco X recomendado para perfil 1
(2, 2), -- Tesouro Selic recomendado para perfil 2
(3, 3); -- Fundo Agressivo Y recomendado para perfil 3

-- Inserindo investimentos
INSERT INTO investimento (titulo, tipo, valor_aplicado, prazo_meses, rentabilidade, data_aplicacao, created_at, updated_at, uuid, investidor_id)
VALUES
('CDB Banco X - Aplicação 1', 'CDB', 1000.00, 12, 0.0125, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1),
('Tesouro Selic - Aplicação 1', 'TESOURO', 2000.00, 24, 0.0100, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2),
('Fundo Agressivo Y - Aplicação 1', 'FUNDO', 1500.00, 18, 0.0250, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3),
('LCI Banco Z - Aplicação 1', 'LCI', 2500.00, 36, 0.0110, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1),
('Fundo Moderado W - Aplicação 1', 'FUNDO', 3000.00, 12, 0.0180, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2),
('LCA Banco Z - Aplicação 1', 'LCA', 1800.00, 24, 0.0200, GETDATE(), GETDATE(), GETDATE(), NEWID(), 3);

---- Inserindo simulações
--INSERT INTO simulacao (produto, valor_investido, valor_final, prazo_meses, data_simulacao, created_at, updated_at, uuid, investidor_id)
--VALUES
--('CDB Banco X', 1500.00, 1700.00, 12, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1),
--('Fundo Agressivo Y', 3000.00, 4000.00, 18, GETDATE(), GETDATE(), GETDATE(), NEWID(), 2),
--('CDB Banco X', 2500.00, 2800.00, 24, GETDATE(), GETDATE(), GETDATE(), NEWID(), 1);
