package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Ciudad;
import com.tuservicios.tuservicios.repository.CiudadRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CiudadController {

    @Autowired
    public CiudadRepository ciudadRepository;

    @PostMapping
    public ResponseEntity<Ciudad> craeteCiudad(@Valid @RequestBody Ciudad ciudad){
        Ciudad newCiudad = ciudadRepository.save(ciudad);
        return new ResponseEntity<>(newCiudad, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Ciudad> finAllCity(){
        return ciudadRepository.findAll();
    }

    @GetMapping("/departamento/{departamentoId}")
    public List<Ciudad> getCiudadByIdDepartamento(@PathVariable Long departamentoId){
        return ciudadRepository.findByDepartamentoId(departamentoId);
    }

    @GetMapping("/{id}")
    public Ciudad getCiudadById(@PathVariable Long id){
        return ciudadRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ciudad no encontrada conID: " + id));

    }

}
