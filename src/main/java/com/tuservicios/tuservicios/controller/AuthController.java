package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.User;
import com.tuservicios.tuservicios.payload.request.LoginRequest;
import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
            String jwt = authService.authenticateUser(loginRequest);
            // Si la autenticaion del usuario es exitosa, obten los datos del usario
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = authService.userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            return  ResponseEntity.ok("Usuario autenticado exitosamente");
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("Credenciales de usuario no validas");
        }
    }

}
