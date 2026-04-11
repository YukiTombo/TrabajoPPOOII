package com.proyecto.gestion_vehiculos.VEHICULO.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "vehiculos", uniqueConstraints = {
    @UniqueConstraint(columnNames = "placa")
})
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tipoVehiculo; // Automovil o Motocicleta

    @Column(nullable = false, unique = true)
    private String placa;

    @NotBlank
    private String tipoServicio; // Pu o Pr

    @NotBlank
    private String tipoCombustible;

    @Min(1)
    private int capacidadPasajeros;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$")
    private String color;

    @Min(1900)
    private int modelo;

    private String marca;

    private String linea;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VehiculoDocumento> documentos;


    // Getters y Setters
}
