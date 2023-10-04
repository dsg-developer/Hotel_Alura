package com.ds.hotel_alura.models.reservas;

import jakarta.validation.constraints.NotNull;

public record ReservaDTO(@NotNull long id, String fechaEntrada, String fechaSalida, int precio, String metodoDePago) {

    public ReservaDTO(Reservas dto) {
        this(dto.getId(), dto.getFecha_entrada(), dto.getFecha_salida(), dto.getPrecio(), 
                dto.getForma_pago());
    }
}
