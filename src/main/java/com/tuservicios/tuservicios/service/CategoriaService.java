package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.Categoria;
import com.tuservicios.tuservicios.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class CategoriaService {

    //Dependency injection of the necessary repositories.
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Create / Update
    @Transactional
    public Categoria saveCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    //Read
    @Transactional(readOnly = true)
    public List<Categoria> findAllActive(){
        return categoriaRepository.findAllByActivoTrue();
    }

    @Transactional
    public Categoria findById(Long id){
        return categoriaRepository.findById(id)
                .filter(Categoria::getActivo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria no encontrada o inactiva con ID: " + id));
    }
    //DELETE logical erase
    @Transactional
    public void  deleteCategoria(long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," Categoria no encontrada con ID: "+id));

                // logical arase
                categoria.setActivo(false);
                categoriaRepository.save(categoria);


    }
}
