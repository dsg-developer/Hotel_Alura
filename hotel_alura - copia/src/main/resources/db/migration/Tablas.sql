/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  DS Team
 * Created: 2 oct 2023
 */

create table reservas(
    id bigint not null auto_increment,
    fecha_entrada char(255) not null,
    fecha_salida char(255) not null,
    precio decimal not null,
    forma_pago char(255),
    primary key(id)
)

//------------------------------------------------------------------------------

create table huespedes(
    id bigint not null auto_increment,
    nombre char(255) not null,
    apellido char(255) not null,
    fecha_nacimiento char(255),
    nacionalidad char(255),
    telefono char(255),
    numero_reserva smallint,
    id_reserva bigint not null,
    primary key(id),
    foreign key(id_reserva) references reservas(id)
)

//------------------------------------------------------------------------------

create table usuarios(
    id bigint not null auto_increment,
    nombre char(255) not null,
    clave char(255) not null,
    primary key(id),
)

//------------------------------------------------------------------------------