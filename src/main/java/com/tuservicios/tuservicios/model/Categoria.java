package com.tuservicios.tuservicios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
public class Categoria extends BaseEntity{

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 250)
    private String descripcion;


}
