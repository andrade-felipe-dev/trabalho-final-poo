-- Tabela Clientes
CREATE TABLE Clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    telefone VARCHAR(20),
    endereco VARCHAR(255),
    email VARCHAR(255),
    saldoAtual DOUBLE,
    limiteFiado DOUBLE,
    id_curso INT,
    FOREIGN KEY (id_curso) REFERENCES Curso(id)
);

-- Tabela Curso
CREATE TABLE Curso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    turno VARCHAR(50)
);

-- Tabela Compras
CREATE TABLE Compras (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dataHora DATETIME,
    estado VARCHAR(50),
    id_cliente INT,
    id_pagamento INT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_pagamento) REFERENCES Pagamentos(id)
);

-- Tabela Pagamentos
CREATE TABLE Pagamentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dataHora DATETIME,
    formaPagamento VARCHAR(50),
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

-- Tabela ItemCompras
CREATE TABLE ItemCompras (
    id INT PRIMARY KEY AUTO_INCREMENT,
    preco DOUBLE,
    id_produto INT,
    id_compra INT,
    FOREIGN KEY (id_produto) REFERENCES Produtos(id),
    FOREIGN KEY (id_compra) REFERENCES Compras(id)
);

-- Tabela Produtos
CREATE TABLE Produtos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    preco DOUBLE
);
