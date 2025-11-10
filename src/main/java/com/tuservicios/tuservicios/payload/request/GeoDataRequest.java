package com.tuservicios.tuservicios.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeoDataRequest {
    @NotBlank
    private  String filePath;
}
