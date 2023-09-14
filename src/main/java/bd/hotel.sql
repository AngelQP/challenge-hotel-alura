create database hotel;

use hotel;

drop table tbl_huespedes;
drop table tbl_reservas;

create table tbl_huespedes(
id int auto_increment,
nombre varchar(50) not null,
apellido varchar(100) not null,
fechaNacimiento date,
nacionalidad varchar(100),
telefono varchar(50),
idReserva int,
primary key(id),
foreign key (idReserva) references tbl_reservas(idReserva) on update cascade on delete cascade
)engine=Innodb;

create table tbl_reservas(
idReserva int auto_increment,
fechaEntrada date,
fechaSalida date,
valor int,
formaPago varchar(50),
primary key(idReserva)
)engine=Innodb;



