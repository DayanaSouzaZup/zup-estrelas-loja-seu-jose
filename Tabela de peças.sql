create database Loja_de_pecas;

create table pecas(
codigo_barra varchar(30) not null primary key,
nome varchar(30) not null,
modelo_carro varchar(30) not null,
fabricante varchar(30) not null,
preço_custo decimal (7,2) not null,
preço_venda decimal (7,2) not null,
quantidade_estoque int not null,
categoria enum ('Som e vídeo', 'Personalização', 'Perfomance')
);

insert into pecas values 
(12345, 'Multimídia', 'Corsa', 'Chevrolet', 1000, 1700, 5, 'Som e vídeo'),
(23456, 'Antena', 'Palio', 'Fiat', 20, 25, 10, 'Som e vídeo'),
(34567, 'Palhetas', 'Kicks', 'Nissan', 35, 50, 5, 'Personalização'),
(45678, 'Acendedor', 'UP', 'VW', 50, 65, 5, 'Personalização'),
(56789, 'Bucha amortecedor', '305', 'Peugeot', 10, 17, 5, 'Perfomance'),
(67890, 'Interruptor de luz', 'Etios', 'Toyota', 150, 180, 5, 'Perfomance');
