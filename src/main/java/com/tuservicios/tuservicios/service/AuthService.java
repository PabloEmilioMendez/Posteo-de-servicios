package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.User;
import com.tuservicios.tuservicios.payload.request.LoginRequest;
import com.tuservicios.tuservicios.payload.request.SingupRequest;
import com.tuservicios.tuservicios.payload.response.JwtResponse;
import com.tuservicios.tuservicios.repository.UserRepository;
import com.tuservicios.tuservicios.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        user.setPhoneNumber(singupRequest.getPhoneNumber());
        //Encriptar la contreseña y guardarla

        String encodedPassword = encoder.encode(singupRequest.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    };

    // AuthService.java
// Asegúrate de importar la clase UserDetails


    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        //Autentica al usuario usando el AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        //Establece la autenticacion en el cotexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Genera el token JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        //Obtiene los detalles del usuario autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //Busca al usuario en la base de datos para obteer el ID y el email
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new RuntimeException("Error: Usuario no encontrado."));

        return new JwtResponse(jwt, user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber());
    }


}
