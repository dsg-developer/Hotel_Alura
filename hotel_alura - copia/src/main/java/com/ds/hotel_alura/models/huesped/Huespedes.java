package com.ds.hotel_alura.models.huesped;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Huespedes {

    public Huespedes(HuespedDTO dto) {
        this.id = dto.id();
        this.nombre = dto.nombre();
        this.apellido = dto.apellido();
        this.fecha_nacimiento = dto.fechaNacimiento();
        this.nacionalidad = dto.nacionalidad();
        this.telefono = dto.telefono();
        this.id_reserva = dto.id_reserva();
    }
    
    @NotBlank
    private long id;
    @NotBlank
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    @NotBlank
    private String nacionalidad;
    @NotBlank
    private String telefono;
    @NotBlank
    private long id_reserva;
}
