package com.tuservicios.tuservicios.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Departemento extends BaseEntity{

    @NotBlank
    @Size(max = 100)
    private  String nombre;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;
}
