package com.tuservicios.tuservicios.payload.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SingupRequest {

    @NotBlank
    @Size(min = 3, max =20)
    private  String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private  String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 10, max = 12)
    private String phoneNumber;

}
