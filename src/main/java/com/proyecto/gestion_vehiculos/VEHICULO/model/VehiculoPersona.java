package com.proyecto.gestion_vehiculos.VEHICULO.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "vehiculo_persona")
public class VehiculoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehiculoId;

    private Long personaId;

    private LocalDate fechaAsociacion;

    private String estado; // PO EA RO

    public VehiculoPersona() {
    }

}
