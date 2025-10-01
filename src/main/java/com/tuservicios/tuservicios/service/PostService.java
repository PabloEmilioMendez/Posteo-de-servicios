package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.*;
import com.tuservicios.tuservicios.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PostService {

    //Inyección de dependencias de los repositorios necesarios.
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    //Crear nuevo Post
    @Transactional
    public Post createPost(Long userId, PostRequest postRequest){

        // Obtener User por Id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        //Obtener Categoria por ID
        Categoria categoria = categoriaRepository.findById(postRequest.getCategoriaId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria no valida"));

        //Obtener Localidda por id
        Localidad localidad = localidadRepository.findById(postRequest.getLocalidad())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidad principal"));

        //Obtener servicios de muchos a muchos
        Set<Servicio> servicios = postRequest.getServicioIds().stream()
                .map(id -> localidadRepository.findById(id).orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servicio con ID " + id + " no encontrado." )))
                        .collect(Collectors.toSet());

        //Obtener localidades de Domicilio
        Set<Localidad> localidadesDomicilio = postRequest.getLocalidadesDomicilioIds().stream()
                .mao(id -> localidadRepository.findById(id).orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Localidad de domicilio con ID " + id + " no encontrasa")))
                        .collect(Collectors.toSet());

        //Mapear PostRequest al modelo Post
        Post post = new Post();
        post.setUser(user);
        post.setCategoria(categoria);
        post.setLocalidad(localidad);
        post.setServicios(servicios);
        post.setDomiciliosLocations(localidadesDomicilio);

        //Mapeo de campos simples
        post.setTitle(post.getTitle());
        post.setDescripcion(post.getDescripcion());
        post.setPhone(post.getPhone());
        post.setAge(post.getAge());
        post.setContactOpcions(post.getContactOpcions());
        post.setWebsiteLink(post.getWebsiteLink());
        post.setMapAddress(post.getMapAddress());
        post.setImageUrls(post.getImageUrls());
        post.setVideoUrls(post.getVideoUrls());
        post.setAdultoContemtAcceptde(post.getAdultoContemtAcceptde());

        return postRepository.save(post);

    }

    //Leer y Buscar Post
    @Transactional(readOnly = true)
    public List<Post> getAllActivePosts(){
        return postRepository.findAllByActivoTrue();
    }

    //Busca post por id si está activo
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id).filter(Post::getActivo);
    }

    //Busca Post por celular
    @Transactional(readOnly = true)
    public List<Post> searchPostsByPhone(String phone){
        return postRepository.findByPhoneAndActivoTrue(phone);
    }

    //Update Post
    @Transactional
    public Post updatePost(Long postId, PostRequest phone){
        Post existingPost = postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        //Logica de actualizacion de relaciones

        //Actualizar Categoria
        if (!existingPost.getCategoria().getId().equals(postRequest.getCategoriaId())){
            Categoria newCategoria = categoriaRepository.findById(postRequest.getCategoriaId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nueva categoria no valida"));
                    existingPost.setCategoria(newCategoria);
        }

        //Actualizar Localidad Principal
        if (!existingPost.getLocalidad().getId().equals(postRequest.getLocalodadID())){
            Localidad newLocalidad = localidadRepository.findById(postRequest.getLocalidadId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nueva localidad no valida"));
            existingPost.setLocalidad(newLocalidad);
        }

        existingPost.getServicios().clear();
        postRequest.getServicioIds().stream()
                .map(id -> servicioRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Servicio con ID " + id + " no encontrado")))
                .forEach(existingPost.getServicios()::add);

        existingPost.getDeliveryLocations().clear();
        postRequest.getDeliveryLocalidadIds().stream()
                .map(id -> localidadRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Localidad con ID " + id + " no encontrada.")))
                .forEach(existingPost.getDeliveryLocations()::add);

        // Mapeo y actualización de campos simples
        existingPost.setTitle(postRequest.getTitle());
        existingPost.setDescription(postRequest.getDescription());
        existingPost.setPhone(postRequest.getPhone());
        existingPost.setAge(postRequest.getAge());
        existingPost.setContactOptions(postRequest.getContactOptions());
        existingPost.setWebsiteLink(postRequest.getWebsiteLink());
        existingPost.setMapAddress(postRequest.getMapAddress());
        existingPost.setImageUrls(postRequest.getImageUrls());
        existingPost.setVideoUrls(postRequest.getVideoUrls());
        existingPost.setAdultContentAccepted(postRequest.getAdultContentAccepted());

        return postRepository.save(existingPost);

    }

    //Borrado logico
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado."));

        // Aplica el borrado lógico usando el campo 'activo' heredado de BaseEntity
        post.setActivo(false);
        postRepository.save(post);
    }

}
