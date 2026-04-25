package com.proyecto.gestion_vehiculos.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String login;
    private String password;
}
