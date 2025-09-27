package com.tuservicios.tuservicios.payload.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;

    public JwtResponse(String token, Long id, String username, String email, String phoneNumber) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
