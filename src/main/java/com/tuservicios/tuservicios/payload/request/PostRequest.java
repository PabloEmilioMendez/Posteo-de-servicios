package com.tuservicios.tuservicios.payload.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class PostRequest {

    @NotNull(message = "La categoria es obligatoria.")
    private  Long categoriaId;

    @NotNull(message = "La localidad principal es obligatoria.")
    private Long localidadId;

    @NotNull(message = "Debe seleccionar al menos un servicio")
    @NotEmpty(message = "Debe seleccionar al menos un servicio")
    private Set<Long> serviciosId;

    private Set<Long> deliveryLocalidadIds;

    @NotBlank(message = "El titulo es obligatorio.")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "La descripcion es obligatoria.")
    @Size(max = 1000)
    private String descripcion;

    @NotBlank(message = "El telefono es obligatorio.")
    private String phone;

    @NotNull(message = "La edad es obligatoria.")
    @Min(value = 18, message = "La edad minima debe ser mayor o igual a 18 años.")
    private Integer age;

    private Set<String> contactOptions;
    private Set<String> imageUrl;
    private Set<String> videoUrl;

    private  String websiteLink;
    private  String mapAddress;

    @NotNull(message = "Debe confirmar las politicas de edad.")
    @AssertTrue(message = "Debe aceptar la politica para publiar.")
    private Boolean adultContentAccepted;

}
