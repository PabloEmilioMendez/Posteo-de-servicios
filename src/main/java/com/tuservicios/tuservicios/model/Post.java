package com.tuservicios.tuservicios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity{

    //Relacion de muchos a uno con user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "user_id", nullable = false)
    private User user;

    //Relacion de muchos a uno con categorias
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    //Relacion de muchos a uno con localidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    //Relacion de muchos a mucho con servicios
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_servicios", //Crea una tabla intermedia relaccional
            joinColumns =  @JoinColumn(name = "post_id"),// Columna FK para el ID del Post en la tabla intermedia
            inverseJoinColumns = @JoinColumn(name = "servicios_id") // Columna FK para el ID del servicio.
    )
    private Set<Servicio> servicios = new HashSet<>();  //Coleccion de servicios asociados al post

    //Relacion de muchos a muchos con domicilios
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_domicilios", //Crea una tabla intermendia relacional
            joinColumns = @JoinColumn(name = "post_id"),//Columna FK para el ID del Post en la tabla intermedia
            inverseJoinColumns = @JoinColumn(name = "localidad_id") //Columna FK para el ID del domicilio
    )
    private Set<Localidad> domiciliosLocations = new HashSet<>();

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 5000)
    private String descripcion;

    @NotBlank
    private String phone;

    @NotBlank
    @Min(18)
    @Max(60)
    private Integer age;

    //Opciones de contacto seleccionadas
    @ElementCollection
    @CollectionTable(name = "post_contact_options", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "contact_otion")
    private  Set<String> contactOpcions = new HashSet<>();

    // URls de Imagenes guardadas como string en una tabla separada
    @ElementCollection
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls = new HashSet<>();

    //URLs de videos guardados como string en una separada
    @ElementCollection
    @CollectionTable(name = "post_videos", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "video_url")
    private Set<String> videoUrls = new HashSet<>();

    private String websiteLink;

    private String mapAddress;

    @NotBlank
    private Boolean adultoContemtAcceptde = false;

}
