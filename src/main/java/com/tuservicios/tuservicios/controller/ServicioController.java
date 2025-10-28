package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Servicio;
import com.tuservicios.tuservicios.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServicioController {

    //Dependency injection of the necessary service
    @Autowired
    public ServicioService servicioService;

    //Get all servicios active
    @GetMapping
    public List<Servicio> findAllActive(){
        return servicioService.findAllActive();

    }
}
