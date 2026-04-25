package com.proyecto.gestion_vehiculos.PERSONA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.gestion_vehiculos.PERSONA.entity.Persona;
import com.proyecto.gestion_vehiculos.PERSONA.repository.PersonaRepository;
import com.proyecto.gestion_vehiculos.USUARIO.entity.Usuario;
import com.proyecto.gestion_vehiculos.USUARIO.entity.UsuarioId;
import com.proyecto.gestion_vehiculos.USUARIO.service.UsuarioService;


@Service
public class PersonaService {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Persona> listar() {
        return repository.findAll();
    }

    public Persona guardar(Persona persona) {

        Persona personaGuardada = repository.save(persona);
    
        Usuario usuario = new Usuario();
    
        String login = generarLogin(persona);
    
        UsuarioId usuarioId =
                new UsuarioId(personaGuardada.getId(), login);
    
        usuario.setId(usuarioId);
        usuario.setPassword(passwordEncoder.encode("123456"));
        usuario.setApiKey(java.util.UUID.randomUUID().toString());
    
        // NUEVO CAMPO ROL
        if (persona.getTipoPersona().equalsIgnoreCase("A")) {
            usuario.setRol("ROLE_ADMIN");
        } else {
            usuario.setRol("ROLE_USER");
        }
    
        usuarioService.guardar(usuario);
    
        return personaGuardada;
    }

    private String generarLogin(Persona p) {

        return p.getNombres().substring(0,1).toLowerCase()
             + p.getApellidos().substring(0,1).toLowerCase()
             + p.getIdentificacion();
    }

    public Persona actualizar(Long id, Persona nuevaPersona) {

        Persona persona = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    
        persona.setIdentificacion(nuevaPersona.getIdentificacion());
        persona.setTipoIdentificacion(nuevaPersona.getTipoIdentificacion());
        persona.setNombres(nuevaPersona.getNombres());
        persona.setApellidos(nuevaPersona.getApellidos());
        persona.setCorreo(nuevaPersona.getCorreo());
        persona.setTipoPersona(nuevaPersona.getTipoPersona());
    
        return repository.save(persona);
    }

    public List<Object[]> totalPorTipo() {
        return repository.totalPorTipo();
    }

}
