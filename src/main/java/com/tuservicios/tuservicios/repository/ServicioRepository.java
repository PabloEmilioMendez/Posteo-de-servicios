package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Categoria, Long> {
}
