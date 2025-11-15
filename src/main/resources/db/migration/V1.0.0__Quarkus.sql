IF NOT EXISTS (
    SELECT * FROM sys.objects
    WHERE object_id = OBJECT_ID(N'[dbo].[produto]') AND type in (N'U')
)
BEGIN
    CREATE TABLE dbo.produto (
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
    SELECT * FROM sys.objects
    WHERE object_id = OBJECT_ID(N'[dbo].[simulacao]') AND type in (N'U')
)
BEGIN
    CREATE TABLE simulacao (
        id BIGINT PRIMARY KEY,
        produto NVARCHAR(255) NOT NULL,
        valor_investido DECIMAL(20,2) NOT NULL,
        valor_final DECIMAL(20,2) NOT NULL,
        prazo_meses INTEGER NOT NULL,
        data_simulacao DATETIME2 NOT NULL,
        created_at DATETIME2 NULL,
        updated_at DATETIME2 NULL,
        uuid NVARCHAR(36) NOT NULL
    );
END;