package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.User;
import com.tuservicios.tuservicios.payload.request.LoginRequest;
import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.repository.UserRepository;
import com.tuservicios.tuservicios.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public User registerUser(SingupRequest singupRequest){
        //Verificamos si el nombre de usuario ya existe
        if(userRepository.existsByUsername(singupRequest.getUsername())) {
            throw new RuntimeException("Error: El nombre del usuario ya está en uso");
        };
        //Verificamos si el correo electronico ya existe
        if(userRepository.existsByEmail(singupRequest.getEmail())){
            throw new RuntimeException("Error: El correo electronico ya esta en uso ");
        };
        // Crear una nueva cuenta de usuario
        User user = new User();
        user.setUsername(singupRequest.getUsername());
        user.setEmail(singupRequest.getEmail());
        //Encriptar la contreseña y guardarla

        String encodedPassword = encoder.encode(singupRequest.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    };

    public String authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.generateJwtToken(authentication);
    };


}
