-- V1__create_tables.sql

IF NOT EXISTS (
    SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[produto_investimento]') AND type = N'U'
)
BEGIN
    CREATE TABLE dbo.produto_investimento (
        id BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
        nome NVARCHAR(255) NOT NULL,
        tipo NVARCHAR(100) NOT NULL,
        rentabilidade DECIMAL(10,4) NOT NULL,
        risco NVARCHAR(50) NOT NULL,
        created_at DATETIME2 NULL,
        updated_at DATETIME2 NULL,
        uuid NVARCHAR(36) NOT NULL
    );
END;

IF NOT EXISTS (
    SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[perfil_investidor]') AND type = N'U'
)
BEGIN
    CREATE TABLE dbo.perfil_investidor (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        titulo NVARCHAR(255) NOT NULL,
        pontuacao DECIMAL(5,2) NOT NULL,
        created_at DATETIME2 NULL,
        updated_at DATETIME2 NULL,
        uuid NVARCHAR(36) NOT NULL,
    );
END;

IF NOT EXISTS (
    SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[investidor]') AND type = N'U'
)
BEGIN
    CREATE TABLE dbo.investidor (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        nome NVARCHAR(255) NOT NULL,
        created_at DATETIME2 NULL,
        updated_at DATETIME2 NULL,
        uuid NVARCHAR(36) NOT NULL,
        perfil_id BIGINT NOT NULL UNIQUE,
        CONSTRAINT fk_investidor_perfil FOREIGN KEY (perfil_id)
            REFERENCES perfil_investidor (id)
            ON DELETE CASCADE
    );
END;

IF NOT EXISTS (
    SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[simulacao]') AND type = N'U'
)
BEGIN
    CREATE TABLE dbo.simulacao (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        produto NVARCHAR(255) NOT NULL,
        valor_investido DECIMAL(20,2) NOT NULL,
        valor_final DECIMAL(20,2) NOT NULL,
        prazo_meses INT NOT NULL,
        data_simulacao DATETIME2 NOT NULL,
        created_at DATETIME2 NULL,
        updated_at DATETIME2 NULL,
        uuid NVARCHAR(36) NOT NULL,
        investidor_id BIGINT NOT NULL,
        CONSTRAINT fk_simulacao_investidor FOREIGN KEY (investidor_id)
            REFERENCES investidor (id)
            ON DELETE CASCADE
    );
END;

IF NOT EXISTS (
    SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[investimento]') AND type = N'U'
)
BEGIN
    CREATE TABLE dbo.investimento (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        titulo NVARCHAR(255) NOT NULL,
        tipo NVARCHAR(255) NOT NULL,
        valor_aplicado DECIMAL(20,2) NOT NULL,
        prazo_meses INT NULL,
        rentabilidade DECIMAL(10,4) NOT NULL,
        data_aplicacao DATETIME2 NOT NULL,
        created_at DATETIME2 NULL,
        updated_at DATETIME2 NULL,
        uuid NVARCHAR(36) NOT NULL,
        investidor_id BIGINT NOT NULL,
        CONSTRAINT fk_investimento_investidor FOREIGN KEY (investidor_id)
            REFERENCES investidor (id)
            ON DELETE CASCADE
    );
END;

IF NOT EXISTS (
    SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[produto_perfil]') AND type = N'U'
)
BEGIN
    CREATE TABLE dbo.produto_perfil (
        produto_id BIGINT NOT NULL,
        perfil_id BIGINT NOT NULL,
        PRIMARY KEY (produto_id, perfil_id),
        CONSTRAINT fk_produto FOREIGN KEY (produto_id)
            REFERENCES produto_investimento (id)
            ON DELETE CASCADE,
        CONSTRAINT fk_perfil FOREIGN KEY (perfil_id)
            REFERENCES perfil_investidor (id)
            ON DELETE CASCADE
    );
END;
