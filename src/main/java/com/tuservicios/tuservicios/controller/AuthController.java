package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest singupRequest){
        try {
            authService.registerUser(singupRequest);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        }catch (RuntimeException e){
            return  ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
