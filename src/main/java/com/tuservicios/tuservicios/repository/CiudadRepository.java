package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    List<Ciudad> findByDepartamentoId(Long departamentoId);
}
