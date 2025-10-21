package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    //Method to abtain active categorias
    List<Categoria> findAllByActivoTrue();

}
