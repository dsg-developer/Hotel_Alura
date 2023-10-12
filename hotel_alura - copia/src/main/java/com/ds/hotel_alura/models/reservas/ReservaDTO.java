package com.ds.hotel_alura.models.reservas;

import jakarta.validation.constraints.NotNull;

public record ReservaDTO(@NotNull long id, String fechaEntrada, String fechaSalida, int precio, String metodoDePago) {

    public ReservaDTO(Reservas entidad) {
        this(entidad.getId(), entidad.getFecha_entrada(), entidad.getFecha_salida(), entidad.getPrecio(), 
                entidad.getForma_pago());
    }
    
    public void setDTO(ReservaDTO dto){
    }

}
