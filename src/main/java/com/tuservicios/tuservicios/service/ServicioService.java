package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.Servicio;
import com.tuservicios.tuservicios.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioService {

    //Dependency injection of the necessary repositories
    @Autowired
    private ServicioRepository servicioRepository;

    //CREATE / UPDATE

    //READ
    @Transactional(readOnly = true)
    public List<Servicio> findAllActive(){
        return servicioRepository.findAllByActivoTrue();
    }
}
