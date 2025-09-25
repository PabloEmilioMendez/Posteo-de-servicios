package com.tuservicios.tuservicios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais extends BaseEntity{
    @NotBlank
    @Size(max = 100)
    private String nombre;
}
