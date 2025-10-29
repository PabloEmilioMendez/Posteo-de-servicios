package com.tuservicios.tuservicios.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaRequest {

    @NotBlank(message = "El nombre de la categoria es obligatorio.")
    private String nombre;

    @NotBlank(message = "La descripcion de la categoria es obligatoria.")
    private String descripcion;
}
