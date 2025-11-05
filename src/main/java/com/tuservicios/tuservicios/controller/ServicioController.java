package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Servicio;
import com.tuservicios.tuservicios.payload.request.ServicioRequest;
import com.tuservicios.tuservicios.repository.ServicioRepository;
import com.tuservicios.tuservicios.service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServicioController {

    //Dependency injection of the necessary service
    @Autowired
    public ServicioService servicioService;

    //CREATE Servicio
    @PostMapping
    public ResponseEntity<Servicio> createServicio(@Valid @RequestBody ServicioRequest servicioRequest){
        Servicio servicio = new Servicio();
        Servicio newServicio = servicioService.saveServicio(servicioRequest, servicio);
        return new ResponseEntity<>(newServicio, HttpStatus.CREATED);
    }

    //Get all servicios active
    @GetMapping
    public List<Servicio> findAllActive(){
        return servicioService.findAllActive();

    }
    //GET id
    @GetMapping("/{id}")
    public Servicio findById(@PathVariable Long id){
        return servicioService.findById(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicios(@PathVariable Long id, @Valid @RequestBody ServicioRequest servicioRequest){
        Servicio existServicio = servicioService.findById(id);
        Servicio updateServicio = servicioService.saveServicio(servicioRequest, existServicio);
        return new ResponseEntity<>(updateServicio, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteServicio(@PathVariable Long id){
        servicioService.deleteServicio(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "El servicio se elimino correctamente.");
        return  ResponseEntity.ok(response);
    }
    //ACTIVATE SERVICE
    @PutMapping("/active/{id}")
    public ResponseEntity<Map<String, Object>> activateServicio(@PathVariable Long id){
        Servicio servicio = servicioService.activateServicio(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message","El servicio se activo correctamente.");
        response.put("servicio", servicio);
        return  ResponseEntity.ok(response);
    }
}
