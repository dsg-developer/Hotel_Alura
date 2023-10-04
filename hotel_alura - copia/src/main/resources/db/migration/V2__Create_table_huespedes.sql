/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Work-Game
 * Created: 26 sep. 2023
 */

create table huespedes(
    id bigint not null auto_increment,
    nombre char(255) not null,
    apellido char(255) not null,
    fecha_nacimiento char(255),
    nacionalidad char(255),
    telefono char(255),
    numero_reserva smallint not null,
    id_reserva bigint not null,
    primary key(id),
    foreign key(id_reserva) references reservas(id)
)
