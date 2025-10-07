package com.tuservicios.tuservicios.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuservicios.tuservicios.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;

import static java.util.Collection.*;

public class UserDetailsImpl implements UserDetails {
    private static  final long serialVersionUID = 1L;
    private  Long id;
    private  String username;
    private  String email;
    private  String phoneNumber;
    @JsonIgnore
    private String password;

    //CONSTRUCTOR
    public UserDetailsImpl(Long id, String username, String email, String phoneNumber, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    //Static method to construct UserDetailsImpl from your usuer entity
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword()
        );
    }
    //KEY METHOD: Allows the PostController to get the ID
    public     Long getId(){
        return id;
    }
    //Implementations of the UserDetails interface (most return true/empty)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}

