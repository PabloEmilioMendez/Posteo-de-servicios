package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Departemento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departemento, Long> {
    //Search for departmensts by the country ID they belong to
    List<Departemento> findByPaisId(Long paisId);
}
