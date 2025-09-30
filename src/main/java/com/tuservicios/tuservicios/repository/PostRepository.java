package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Marcar interfaz como un componente de repositorio de String
public interface PostRepository extends JpaRepository<Post, Long> {


    //Metodo para buscar post activos por id categorias
    List<Post> findByCategoriaIdAndActivoTrue(Long categoriaId);

    //Metodo para buscar  post activos por Localidad
    List<Post> findByLocalidadIdAndActivoTrue(Long localidadId);

    //Metodo para buscar post activos por id Usuario
    List<Post> findByUserIdAndActivoTrue(Long userId);

    //Metodo para buscar post cuyo estado sea activo
    List<Post> findAllByActivoTrue();

    //Metodo para consultar post activos  por celular
    List<Post> findByPhoneAndActivoTrue(String phone);
}
