package com.tuservicios.tuservicios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalDateTime fechaDeRegistro;

    private LocalDateTime fechaDeModificacion;

    private Boolean activo = true;

    @PrePersist
    protected void onCreate(){
        this.fechaDeRegistro = LocalDateTime.now();
        this.fechaDeModificacion = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUdpate(){
        this.fechaDeModificacion = LocalDateTime.now();
    }
}
