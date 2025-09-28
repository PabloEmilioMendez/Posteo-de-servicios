package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Marcar interfaz como un componente de repositorio de String
public interface PostRepository extends JpaRepository<Post, Long> {

    //Metodo para buscar post por categorias por id
    List<Post> findByCategoriaId(Long categoriaId);

    //Metodo para buscar  post por Localidad por id
    List<Post> finByLocalidadId(Long localidad);

    //Metodo para buscar post por idUsuario
    List<Post> finByUserId(Long userId);

    //Metodo para buscar post por estado
    List<Post> findAllByActivoTrue();
}
