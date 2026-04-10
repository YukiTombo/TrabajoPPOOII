package com.proyecto.gestion_vehiculos.VEHICULO.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoDTO {

    @NotBlank
    private String tipoVehiculo;

    @NotBlank
    private String placa;

    @NotBlank
    private String tipoServicio;

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

    // Getters y Setters

}
