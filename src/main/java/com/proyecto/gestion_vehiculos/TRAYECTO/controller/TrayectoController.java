package com.proyecto.gestion_vehiculos.TRAYECTO.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion_vehiculos.TRAYECTO.entity.Trayecto;
import com.proyecto.gestion_vehiculos.TRAYECTO.service.TrayectoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trayectos")
@RequiredArgsConstructor
public class TrayectoController {

    private final TrayectoService trayectoService;

    @PostMapping
    public ResponseEntity<?> crear(
            @RequestBody Trayecto trayecto
    ){
        return ResponseEntity.ok(
                trayectoService.crear(trayecto)
        );
    }

    @GetMapping("/ruta/{codigo}")
    public ResponseEntity<?> ruta(
            @PathVariable String codigo
    ){
        return ResponseEntity.ok(
                trayectoService.consultarRuta(codigo)
        );
    }

    @GetMapping("/conductor/{identificacion}")
    public ResponseEntity<?> conductor(
            @PathVariable String identificacion
    ){
        return ResponseEntity.ok(
                trayectoService
                        .consultarRutasConductor(
                                identificacion
                        )
        );
    }

    @GetMapping("/vehiculo/{placa}")
    public ResponseEntity<?> vehiculo(
            @PathVariable String placa
    ){
        return ResponseEntity.ok(
                trayectoService.consultarVehiculo(placa)
        );
    }
}
