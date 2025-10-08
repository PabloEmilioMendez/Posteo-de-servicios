package com.tuservicios.tuservicios.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaRequest {

    @NotNull(message = "El nombre de la categoria es obligatorio.")
    private String nombre;

    @NotNull(message = "La descripcion de la categoria es obligatoria.")
    private String descripcion;
}
