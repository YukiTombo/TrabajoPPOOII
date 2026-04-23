package com.proyecto.gestion_vehiculos.VEHICULO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion_vehiculos.VEHICULO.model.Vehiculo;
import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoDocumento;
import com.proyecto.gestion_vehiculos.VEHICULO.service.VehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService service;

    @GetMapping
    public List<Vehiculo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(@PathVariable Long id, @Valid @RequestBody Vehiculo vehiculo) {
        return service.actualizar(id, vehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PostMapping("/con-documentos")
    public Vehiculo crearConDocumentos(
        @RequestBody Vehiculo vehiculo,
        @RequestParam(required = false) List<Long> documentosIds) {

        return service.guardarConDocumentos(vehiculo, documentosIds);
    }

    @GetMapping("/{id}/documentos")
    public List<VehiculoDocumento> obtenerDocumentos(@PathVariable Long id) {
        return service.obtenerDocumentosPorVehiculo(id);
    }

    // Buscar por placa
    @GetMapping("/placa/{placa}")
    public Vehiculo buscarPorPlaca(@PathVariable String placa) {
        return service.buscarPorPlaca(placa);
    }

    // Buscar por tipo vehículo
    @GetMapping("/tipo")
    public List<Vehiculo> buscarPorTipo(@RequestParam String tipo) {
        return service.buscarPorTipo(tipo);
    }

    // Buscar por tipo de documento
    @GetMapping("/documento")
    public List<Vehiculo> buscarPorDocumento(@RequestParam String nombre) {
        return service.buscarPorDocumento(nombre);
    }

    // Buscar por estado del documento
    @GetMapping("/estado")
    public List<Vehiculo> buscarPorEstado(@RequestParam String estado) {
        return service.buscarPorEstadoDocumento(estado);
    }
}
