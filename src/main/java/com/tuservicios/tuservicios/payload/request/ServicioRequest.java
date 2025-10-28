package com.tuservicios.tuservicios.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicioRequest {

    @NotNull(message = "El nombre del servicio es obligatorio")
    private String nombre;


    @NotNull(message = "La descripciòn del servicio es obligatoria")
    private String description;
}
