package com.proyecto.gestion_vehiculos.USUARIO.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @EmbeddedId
    private UsuarioId id;

    private String password;

    private String apiKey;
    
    private String rol;

    public Usuario() {
    }
}
