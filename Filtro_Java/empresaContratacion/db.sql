create database empresa_contratacion;

use empresa_contratacion;

create table empresa (
	id int auto_increment primary key,
    nombre varchar(255) not null,
    sector varchar(255) not null,
    ubicacion varchar(255) not null,
    contacto varchar(255) not null
);

create table vacante (
	id int auto_increment primary key,
    titulo varchar(255) not null,
    descripcion text not null,
    duracion varchar(255) not null,
    estado varchar(255) not null check(estado in ("activo","inactivo")),
    id_empresa int,
	foreign key (id_empresa) references empresa(id) on delete cascade
);

create table coder (
	id int auto_increment primary key,
    nombre varchar(255) not null,
    apellidos varchar(255) not null,
    documento varchar(255) not null unique,
    cohorte int not null,
    cv text not null
);

create table contratacion (
	id int auto_increment primary key,
    fecha_aplicacion timestamp default current_timestamp,
    estado varchar(255) not null check(estado in ("activo","inactivo")),
    salario decimal(10,2) not null,
    id_vacante int,
    id_coder int,
    foreign key (id_vacante) references vacante(id) on delete cascade,
    foreign key (id_coder) references coder(id) on delete cascade
);

alter table vacante add tecnologia varchar(255) not null;
alter table coder add clan varchar(255) not null;

insert into empresa(nombre, sector, ubicacion, contacto) VALUES ("empresa1","informatico","medellin","+123 456789");
insert into empresa(nombre, sector, ubicacion, contacto) VALUES ("empresa2","informatico","bogota","+123 222222");
insert into empresa(nombre, sector, ubicacion, contacto) VALUES ("empresa3","informatico","medellin","+123 456789");





