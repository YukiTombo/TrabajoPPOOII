package com.proyecto.gestion_vehiculos.PERSONA.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Persona {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacion;
    private String tipoIdentificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private String tipoPersona; // C o A

    // getters y setters
}
