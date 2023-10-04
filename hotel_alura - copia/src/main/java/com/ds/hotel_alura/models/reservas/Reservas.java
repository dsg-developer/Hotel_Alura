package com.ds.hotel_alura.models.reservas;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Reservas{

    public Reservas(ReservaDTO dto) {
        this.id = dto.id();
        this.fecha_entrada = dto.fechaEntrada();
        this.fecha_salida = dto.fechaSalida();
        this.precio = dto.precio();
        this.forma_pago = dto.metodoDePago();
    }
        
    @NotBlank
    private long id;
    @NotBlank
    private String fecha_entrada;
    @NotBlank
    private String fecha_salida;
    @NotBlank
    private int precio;
    @NotBlank
    private String forma_pago;
}
