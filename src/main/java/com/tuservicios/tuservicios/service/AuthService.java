package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.User;
import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

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

    }
}
