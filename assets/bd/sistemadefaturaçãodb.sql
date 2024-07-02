create database sistemadefaturacaodb;
use sistemadefaturacaodb;
Create table clientes (
	id_cliente int primary key not null auto_increment,
    nome_cliente varchar(100) not null,
    nif_cliente varchar (14)
);
Create table fornecedores (
	id_fornecedor int primary key not null auto_increment,
    nome_fornecedor varchar(100) not null,
    nif_fornecedor varchar (14)
);
Create table funcionarios (
	id_funcionario int primary key not null auto_increment,
    nome_funcionario varchar(100) not null,
    nif_funcionario varchar (14)
);
create table produtos (
	id_produto int primary key not null auto_increment,
    nome_produto varchar(100),
    codigo_barra varchar (20),
    preco decimal (10,2),
    quantidade int,
    id_fornecedor int,
    foreign key (id_fornecedor) references fornecedores (id_fornecedor)
);
create table carrinho (
	id_carrinho  int primary key not null auto_increment,
    id_fatura int,
    nome_produto varchar(100),
    codigo_barra varchar (20),
    quantidade int, 
    preco decimal (10,2),
    total decimal (10,2)
);
create table venda (
id_venda int primary key not null auto_increment,
id_fatura int,
id_cliente int,
nome_cliente varchar(100),
quantidade_total decimal (10,2),
total decimal(10,2),
estado varchar(50),
saldo decimal (10,2),
foreign key (id_cliente) references Clientes (id_cliente)
);
create table extra (
	id_extra int primary key not null auto_increment,
    valor varchar (10)
);
create table usuario (
id_usuario int primary key not null auto_increment,
nome varchar(100),
senha varchar (50),
tipo varchar (20)
);
insert into usuario (nome,senha,tipo) values ('admin', '123','Admin');
SELECT 
    carrinho.id_fatura, 
    carrinho.nome_produto, 
    carrinho.codigo_barra, 
    carrinho.quantidade, 
    carrinho.preco AS preco_unitario, 
    carrinho.total AS preco_total, 
    venda.total AS valor_total, 
    venda.saldo AS troco  
FROM 
    carrinho
INNER JOIN 
    venda ON carrinho.id_fatura = venda.id_fatura
WHERE 
    carrinho.id_fatura = 2;


select *from clientes;
select *from fornecedores;
select *from funcionarios;
select *from produtos;
select*from carrinho;
select *from venda;

select *from extra;

SHOW TABLES IN sistemadefaturacaodb;
INSERT INTO fornecedores (nome_fornecedor, nif_fornecedor) VALUES ('Ana Maria', '123456789LA012');
INSERT INTO fornecedores (nome_fornecedor, nif_fornecedor) VALUES ('João Pereira', '987654321LA034');
INSERT INTO fornecedores (nome_fornecedor, nif_fornecedor) VALUES ('Carlos Silva', '456123789LA056');
INSERT INTO fornecedores (nome_fornecedor, nif_fornecedor) VALUES ('Mariana Costa', '321987654LA078');
INSERT INTO fornecedores (nome_fornecedor, nif_fornecedor) VALUES ('Pedro Albuquerque', '654321987LA090');