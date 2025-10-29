package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Categoria;
import com.tuservicios.tuservicios.payload.request.CategoriaRequest;
import com.tuservicios.tuservicios.repository.CategoriaRepository;
import com.tuservicios.tuservicios.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //Create Category
    @PostMapping
    //@PreAuthorize("isAutheticated()") // Only authenticated users can create
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest){
        Categoria categoria = new Categoria();
        Categoria newCategory = categoriaService.saveCategoria(categoriaRequest,categoria);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED); // Returns the created categoria with code 201 CREATED
    }
    //READ all active categories (GET /api/categories
    @GetMapping
    public List<Categoria> getAllCategorias(){
        return categoriaService.findAllActive();
    }
    // READ BY ID (GET /api/categorias/{id})
    @GetMapping("/{id}")
    public Categoria getCategiriaById(@PathVariable Long id){
        return categoriaService.findById(id);
    }
    //UPDATE (PUT /api/categorias/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequest categoriaRequest){

        Categoria existingCategiria = categoriaService.findById(id); //Busca y lanza 404 si no existe/activa
        Categoria updatedCategoria = categoriaService.saveCategoria(categoriaRequest, existingCategiria);

        return  new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
    }
    //DELETE (DELETE /api/categorias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategoria(@PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //
    @PutMapping("/active/{id}")
    public ResponseEntity<HttpStatus> activeCategorie(@PathVariable Long id){
        categoriaService.activeCategorie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
