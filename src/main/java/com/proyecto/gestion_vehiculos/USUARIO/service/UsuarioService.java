package com.proyecto.gestion_vehiculos.USUARIO.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestion_vehiculos.USUARIO.entity.Usuario;
import com.proyecto.gestion_vehiculos.USUARIO.entity.UsuarioId;
import com.proyecto.gestion_vehiculos.USUARIO.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario guardar(Usuario u) {
        return repository.save(u);
    }

    public Usuario cambiarPassword(Long personaId, String login, String nuevaPassword) {

        UsuarioId id = new UsuarioId(personaId, login);

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setPassword(nuevaPassword);

        return repository.save(usuario);
    }

    public Usuario regenerarApiKey(Long personaId, String login) {

        UsuarioId id = new UsuarioId(personaId, login);

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setApiKey(UUID.randomUUID().toString());

        return repository.save(usuario);
    }
}