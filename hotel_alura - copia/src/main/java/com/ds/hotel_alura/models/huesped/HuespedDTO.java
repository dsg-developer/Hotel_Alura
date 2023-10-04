package com.ds.hotel_alura.models.huesped;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record HuespedDTO(
        @NotBlank long id, 
        @NotBlank String nombre, 
        String apellido, 
        String fechaNacimiento,
        String nacionalidad,
        @NotBlank String telefono,
        @NotBlank long id_reserva
    ) {

}
