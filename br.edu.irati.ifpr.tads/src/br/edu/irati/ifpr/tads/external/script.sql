CREATE TABLE banco.cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    turno VARCHAR(50)
);

CREATE TABLE banco.clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    telefone VARCHAR(20),
    endereco VARCHAR(255),
    email VARCHAR(255),
    saldo_atual DOUBLE,
    limite_fiado DOUBLE,
    id_curso INT,
    FOREIGN KEY (id_curso) REFERENCES banco.cursos(id)
);

CREATE TABLE banco.pagamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_hora DATETIME,
    forma_pagamento VARCHAR(50),
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES banco.clientes(id)
);

CREATE TABLE banco.produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    preco DOUBLE
);

CREATE TABLE banco.compras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_hora DATETIME,
    estado VARCHAR(50),
    id_cliente INT,
    id_pagamento INT,
    FOREIGN KEY (id_cliente) REFERENCES banco.clientes(id),
    FOREIGN KEY (id_pagamento) REFERENCES banco.pagamentos(id)
);

CREATE TABLE banco.item_compras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT,
    id_compra INT,
    FOREIGN KEY (id_produto) REFERENCES banco.produtos(id),
    FOREIGN KEY (id_compra) REFERENCES banco.compras(id)
);
