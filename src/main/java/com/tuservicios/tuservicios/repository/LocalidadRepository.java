package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalidadRepository extends JpaRepository<Ciudad, Long> {
}
