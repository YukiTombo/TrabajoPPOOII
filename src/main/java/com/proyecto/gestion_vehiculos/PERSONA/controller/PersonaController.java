package com.proyecto.gestion_vehiculos.PERSONA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion_vehiculos.PERSONA.entity.Persona;
import com.proyecto.gestion_vehiculos.PERSONA.service.PersonaService;
import com.proyecto.gestion_vehiculos.response.ApiResponse;


@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @PostMapping
    public ResponseEntity<ApiResponse> crear(@RequestBody Persona p) {
        service.crear(p);
        return ResponseEntity.ok(new ApiResponse("Persona creada con éxito"));
    }

    @GetMapping
    public List<Persona> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
        @RequestBody Persona persona) {
            return ResponseEntity.ok(service.actualizar(id, persona));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(
        @PathVariable Long id) {

            service.eliminar(id);
            return ResponseEntity.ok(
                new ApiResponse("Persona eliminada correctamente")
        );
    }


}
