package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.Categoria;
import com.tuservicios.tuservicios.model.Servicio;
import com.tuservicios.tuservicios.payload.request.ServicioRequest;
import com.tuservicios.tuservicios.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicioService {

    //Dependency injection of the necessary repositories
    @Autowired
    private ServicioRepository servicioRepository;

    //CREATE / UPDATE SERVICIO
    @Transactional
    public Servicio saveServicio(ServicioRequest servicioRequest, Servicio servicio){
        servicio.setNombre(servicioRequest.getNombre());
        servicio.setDescripcion(servicioRequest.getDescription());
        return servicioRepository.save(servicio);
    }

    //READ
    @Transactional(readOnly = true)
    public List<Servicio> findAllActive(){
        return servicioRepository.findAllByActivoTrue();
    }
    //READ POR ID
    @Transactional
    public Servicio findById(Long id){
        return servicioRepository.findById(id).filter(Servicio :: getActivo)
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio con id no encontrado: "+ id));
    }
    //DELETE Servicio
    @Transactional
    public void deleteServicio(Long id){
        Servicio servicio = servicioRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado con ID: " +id));
        servicio.setActivo(false);
        servicioRepository.save(servicio);
    }

    //ACTIVATE SERVICE
    @Transactional
    public Servicio activateServicio(Long id){
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Servicio no encontrado con ID: " + id));
        servicio.setActivo(true);
        return servicioRepository.save(servicio);
    }

}
