package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Departemento;
import com.tuservicios.tuservicios.repository.DepartamentoRepository;
import com.tuservicios.tuservicios.repository.PaisRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DepartamentoController {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private PaisRepository paisRepository;

    public ResponseEntity<Departemento> createDepartamento(@Valid @RequestBody Departemento departemento){
        Departemento newDepartamento = departamentoRepository.save(departemento);
        return  new ResponseEntity<>(newDepartamento, HttpStatus.CREATED);
    }
    //GET ALL Departamento
    public List<Departemento> getAllDepartamento(){
        return departamentoRepository.findAll();
    }
    //GET Departamento By ID
    public  List<Departemento> getDepartamentosById(@PathVariable Long paisId){
        return departamentoRepository.findByPaisId(paisId);
    }
}
