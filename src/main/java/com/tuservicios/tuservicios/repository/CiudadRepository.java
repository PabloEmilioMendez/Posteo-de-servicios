package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
