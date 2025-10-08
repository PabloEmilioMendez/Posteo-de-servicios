package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

//Dependency injection of the necessary repositories.
    @Autowired
    private CategoriaRepository categoriaRepository;
}
