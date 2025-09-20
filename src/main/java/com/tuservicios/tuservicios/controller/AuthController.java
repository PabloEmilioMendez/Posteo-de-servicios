package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.User;
import com.tuservicios.tuservicios.payload.request.LoginRequest;
import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.payload.response.JwtResponse;
import com.tuservicios.tuservicios.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

            String jwt = authService.authenticateUser(loginRequest);
            // Si la autenticacion del usuario es exitosa, obten los datos del usuario
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = authService.userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

            return ResponseEntity.ok(new JwtResponse(jwt, user.getId(), user.getUsername(), user.getEmail()));

    }

    @GetMapping("/api/test/authenticated")
    @PreAuthorize("isAuthenticated()")
    public String testAuthenticated(){
        return "Has accedido a un recurso protegido";
    }

}
