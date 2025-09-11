package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.payload.request.LoginRequest;
import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins =  "*", maxAge = 3600)
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

    @PostMapping("/signin")
    public ResponseEntity<?> authencateUser(@Valid @RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authService.authenticateUser(loginRequest);
            // Se retorna el JWT
            return  ResponseEntity.ok("Usuario autenticado exitosamente");
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("Credenciales de usuario no validas");
        }
    }

}
