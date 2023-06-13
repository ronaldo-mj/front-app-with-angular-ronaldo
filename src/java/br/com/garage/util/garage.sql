/* Tabela marca */
CREATE TABLE marca(
    idmarca SERIAL NOT NULL,
    nomemarca VARCHAR(220) NOT NULL,
    descricaomarca VARCHAR(220),
    CONSTRAINT pk_marca PRIMARY KEY (idmarca)
);

/* Tabela veiculo com FK de marca*/
CREATE TABLE veiculo(
    idveiculo SERIAL NOT NULL,
    nomeveiculo VARCHAR(220) NOT NULL,
    anofabricacaoveiculo INT NOT NULL,
    valorveiculo NUMERIC(10,2) NOT NULL,
    idmarca INT,
    CONSTRAINT pk_veiculo PRIMARY KEY (idveiculo),
    CONSTRAINT fk_veiculo FOREIGN KEY (idmarca) REFERENCES marca (idmarca)
);

/* Tabela pessoa*/
CREATE TABLE pessoa(
    idpessoa SERIAL NOT NULL,
    nomepessoa VARCHAR (500) NOT NULL,
    cpfpessoa VARCHAR (14) UNIQUE NOT NULL , 
    rgpessoa VARCHAR (9) UNIQUE NOT NULL,
    emailpessoa VARCHAR (220) UNIQUE NOT NULL,
    senhapessoa VARCHAR (220) NOT NULL,
    tipopessoa VARCHAR (1) NOT NULL,
    CONSTRAINT pk_pessoa PRIMARY KEY (idpessoa)
);

/* Tabela administrador com FK de pessoa*/
CREATE TABLE administrador(
    idadministrador SERIAL NOT NULL,
    funcaoadministrador VARCHAR (220) NOT NULL,
    idpessoa INT NOT NULL,
    CONSTRAINT pk_administrador PRIMARY KEY (idadministrador),
    CONSTRAINT fk_administrador_pessoa FOREIGN KEY (idpessoa) REFERENCES pessoa (idpessoa)
);

INSERT INTO pessoa VALUES (1, 'Matheus', '44544713803', '384093061', 'admin@gmail.com', MD5('admin'), 'A');
INSERT INTO administrador VALUES (1, 'Vendedor de automoveis', 1);

INSERT INTO marca (idmarca, nomemarca, descricaomarca) VALUES (1, 'Chevrolet', 'Foi fundada em 1911 por Louis Chevrolet');
INSERT INTO marca (idmarca, nomemarca, descricaomarca) VALUES (2, 'Ferrari', 'Foi fundada em 1939 por Enzo Ferrari');
INSERT INTO marca (idmarca, nomemarca, descricaomarca) VALUES (3, 'Volksvagen', 'Foi fundada em 1937 por Frente Alemã para o Trabalho');
INSERT INTO marca (idmarca, nomemarca, descricaomarca) VALUES (4, 'Fiat', 'Foi fundada em 1899 por Giovanni Agnelli');
INSERT INTO marca (idmarca, nomemarca, descricaomarca) VALUES (5, 'Audi', 'Foi fundada em 1909 por August Horch');