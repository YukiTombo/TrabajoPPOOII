package com.proyecto.gestion_vehiculos.PERSONA.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.gestion_vehiculos.PERSONA.entity.Persona;
import com.proyecto.gestion_vehiculos.PERSONA.repository.PersonaRepository;
import com.proyecto.gestion_vehiculos.USUARIO.entity.Usuario;
import com.proyecto.gestion_vehiculos.USUARIO.entity.UsuarioId;
import com.proyecto.gestion_vehiculos.USUARIO.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    // =========================
    // CREAR PERSONA
    // =========================
    public Persona crear(Persona persona){

        Persona personaGuardada =
                personaRepository.save(persona);

        // CREAR USUARIO SI ES ADMIN
        if(persona.getTipoPersona().equals("A")){

            Usuario usuario = new Usuario();

            String login =
                    persona.getNombres()
                            .substring(0,1)
                            .toLowerCase()
                    +
                    persona.getApellidos()
                            .substring(0,1)
                            .toLowerCase()
                    +
                    persona.getIdentificacion();

            UsuarioId usuarioId = new UsuarioId();

            usuarioId.setPersonaId(
                    personaGuardada.getId()
            );

            usuarioId.setLogin(login);

            usuario.setId(usuarioId);

            usuario.setPassword(
                    passwordEncoder.encode("123456")
            );

            usuario.setApiKey(
                    UUID.randomUUID().toString()
            );

            usuario.setRol("ADMIN");

            usuarioRepository.save(usuario);
        }

        return personaGuardada;
    }

    // =========================
    // LISTAR
    // =========================
    public List<Persona> listar(){
        return personaRepository.findAll();
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Persona buscar(Long id){

        return personaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Persona no encontrada"
                        ));
    }

    // =========================
    // ACTUALIZAR
    // =========================
    public Persona actualizar(
            Long id,
            Persona nuevaPersona
    ){

        Persona persona =
                personaRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Persona no encontrada"
                                )
                        );

        persona.setIdentificacion(
                nuevaPersona.getIdentificacion()
        );

        persona.setTipoIdentificacion(
                nuevaPersona.getTipoIdentificacion()
        );

        persona.setNombres(
                nuevaPersona.getNombres()
        );

        persona.setApellidos(
                nuevaPersona.getApellidos()
        );

        persona.setCorreo(
                nuevaPersona.getCorreo()
        );

        persona.setTipoPersona(
                nuevaPersona.getTipoPersona()
        );

        // ENTREGA 3
        persona.setLicenciaConduccion(
                nuevaPersona.getLicenciaConduccion()
        );

        persona.setFechaVigenciaLicencia(
                nuevaPersona.getFechaVigenciaLicencia()
        );

        return personaRepository.save(persona);
    }

    // =========================
    // ELIMINAR
    // =========================
    public void eliminar(Long id){

        personaRepository.deleteById(id);
    }
}