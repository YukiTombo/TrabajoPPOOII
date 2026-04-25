package com.proyecto.gestion_vehiculos.USUARIO.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion_vehiculos.USUARIO.entity.Usuario;
import com.proyecto.gestion_vehiculos.USUARIO.entity.UsuarioId;

public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioId>{

    Optional<Usuario> findById_Login(String login);
    Optional<Usuario> findByApiKey(String apiKey);
}
