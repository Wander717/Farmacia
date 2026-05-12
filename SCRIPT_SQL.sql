drop database if exists farmacia;
create database farmacia;
use farmacia;

create table cliente (
id_cliente int primary key not null auto_increment,
nome_cliente varchar(20) not null,
idade_cliente int (3) not null
);

create table funcionario (
id_funcionario int primary key not null auto_increment,
nome_funcionario varchar (20) not null,
cargo_funcionario varchar (20) not null
);

create table remedio (
id_remedio int primary key not null auto_increment,
nome_remedio varchar (20) not null,
tipo_remedio varchar (20) not null,
quantidade_remedio int (10) not null
);

-- Suas tabelas anteriores continuam iguais...

create table registros (
    id_registro int primary key not null auto_increment,
    
    -- Colunas que vão armazenar os IDs das outras tabelas
    fk_id_cliente int not null,
    fk_id_funcionario int not null,
    fk_id_remedio int not null,
    
    data_registro timestamp default current_timestamp,
    quantidade_vendida int not null,

    -- Definindo as Foreign Keys
    constraint fk_cliente_registro 
        foreign key (fk_id_cliente) references cliente(id_cliente),
        
    constraint fk_funcionario_registro 
        foreign key (fk_id_funcionario) references funcionario(id_funcionario),
        
    constraint fk_remedio_registro 
        foreign key (fk_id_remedio) references remedio(id_remedio)
);
	
