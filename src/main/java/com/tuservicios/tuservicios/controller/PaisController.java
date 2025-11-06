package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Pais;
import com.tuservicios.tuservicios.repository.PaisRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paises")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaisController {
    @Autowired
    private PaisRepository paisRepository;

    //CREATE COUNTRY
    @PostMapping
    public ResponseEntity<Pais> createCountry(@Valid @RequestBody Pais pais){
        Pais newPais = paisRepository.save(pais);
        return new ResponseEntity<>(newPais, HttpStatus.OK);
    }
}
