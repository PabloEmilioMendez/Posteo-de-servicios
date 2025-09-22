package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.payload.request.LoginRequest;
import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.payload.response.JwtResponse;
import com.tuservicios.tuservicios.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SingupRequest singupRequest) {
        try {
            authService.registerUser(singupRequest);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales de usuario no válidas");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/test/authenticated")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> testAuthenticated() {
        return ResponseEntity.ok("Has accedido a un recurso protegido");
    }
}