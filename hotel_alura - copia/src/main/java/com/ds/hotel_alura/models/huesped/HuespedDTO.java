package com.ds.hotel_alura.models.huesped;

import jakarta.validation.constraints.NotBlank;

public record HuespedDTO(
        @NotBlank long id, 
        @NotBlank String nombre, 
        String apellido, 
        String fechaNacimiento,
        String nacionalidad,
        @NotBlank String telefono,
        @NotBlank long id_reserva
    ) {
    public HuespedDTO(Huespedes entidad) {
        this(entidad.getId(), entidad.getNombre(), entidad.getApellido(), 
                entidad.getFecha_nacimiento(), entidad.getNacionalidad(),
                entidad.getTelefono(),
                entidad.getId_reserva());
    }
    
    public void setDTO(HuespedDTO dto){
    }
}
