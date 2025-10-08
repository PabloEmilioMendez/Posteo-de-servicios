package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Categoria;
import com.tuservicios.tuservicios.payload.request.CategoriaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;// Business layer injection

    //Create Categoria
    @PostMapping
    @PreAuthorize("isAutheticated()") // Only authenticated users can create
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest){
        Categoria newCategory = categoriaService.createCategoria(categoriaRequest);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED); // Returns the created categoria with code 201 CREATED
    }

}
