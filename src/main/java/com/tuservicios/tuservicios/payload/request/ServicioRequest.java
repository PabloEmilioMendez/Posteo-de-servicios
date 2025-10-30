package com.tuservicios.tuservicios.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicioRequest {

    @NotBlank(message = "El nombre del servicio es obligatorio")
    private String nombre;


    @NotBlank(message = "La descripciòn del servicio es obligatoria")
    private String description;
}
