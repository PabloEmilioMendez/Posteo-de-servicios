package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Ciudad;
import com.tuservicios.tuservicios.model.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
}
