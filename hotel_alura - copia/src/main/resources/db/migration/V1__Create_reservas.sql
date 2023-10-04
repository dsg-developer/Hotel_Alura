/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Work-Game
 * Created: 26 sep. 2023
 */

create table reservas(
    id bigint not null auto_increment,
    fecha_entrada char(255) not null,
    fecha_salida char(255) not null,
    precio decimal not null,
    forma_pago char(255),
    primary key(id)
)
