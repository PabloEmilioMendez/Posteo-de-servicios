package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Categoria;
import com.tuservicios.tuservicios.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    //List active services
    public List<Servicio> findAllByActivoTrue();
}
