package com.proyecto.gestion_vehiculos.USUARIO.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.gestion_vehiculos.USUARIO.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PutMapping("/{personaId}/{login}/password")
    public ResponseEntity<?> cambiarPassword(
            @PathVariable Long personaId,
            @PathVariable String login,
            @RequestBody String nuevaPassword) {

        return ResponseEntity.ok(
                service.cambiarPassword(personaId, login, nuevaPassword)
        );
    }

    @GetMapping("/{personaId}/{login}/apikey")
    public ResponseEntity<?> regenerarApiKey(
            @PathVariable Long personaId,
            @PathVariable String login) {

        return ResponseEntity.ok(
                service.regenerarApiKey(personaId, login)
        );
    }
}