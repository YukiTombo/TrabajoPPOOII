package com.proyecto.gestion_vehiculos.VEHICULO.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.proyecto.gestion_vehiculos.response.ApiResponse;

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

import com.proyecto.gestion_vehiculos.VEHICULO.dto.VehiculoDocumentoDTO;
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
    public ResponseEntity<ApiResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Vehiculo vehiculo) {
    
        service.actualizar(id, vehiculo);
    
        return ResponseEntity.ok(new ApiResponse("Vehículo actualizado con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(new ApiResponse("Vehículo eliminado con éxito"));
    }

    @PostMapping("/con-documentos")
    public ResponseEntity<ApiResponse> crearConDocumentos(
        @RequestBody Vehiculo vehiculo,
        @RequestParam List<Long> documentosIds) {
            service.guardarConDocumentos(vehiculo, documentosIds);

        return ResponseEntity.ok(new ApiResponse("Vehículo creado con documentos correctamente"));
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

    //Cargar Documentos
    @PostMapping("/documentos/cargar")
    public ResponseEntity<ApiResponse> cargarDocumentos(
        @RequestParam Long vehiculoId,
        @RequestBody List<VehiculoDocumentoDTO> documentos) {

        service.cargarDocumentosDTO(vehiculoId, documentos);

        return ResponseEntity.ok(new ApiResponse("Documentos cargados correctamente"));
    }

    @PostMapping("/asociar-conductor")
    public String asociarConductor(
        @RequestParam Long vehiculoId,
        @RequestParam Long personaId) {
            
            service.asociarConductor(vehiculoId, personaId);

            return "Conductor asociado correctamente";
    }

    //BUSCAR DOCUMENTOS VENCIDOS
    @GetMapping("/vencidos")
    public List<Vehiculo> obtenerVencidos() {
        return service.obtenerVehiculosConDocumentosVencidos();
    }

    //Buscar Veihuclos con documentos a vencer
    @GetMapping("/por-vencer/{dias}")
    public ResponseEntity<?> porVencer(@PathVariable int dias) {
        return ResponseEntity.ok(service.porVencer(dias));
    }

    //Conductores que pueden operar
    @GetMapping("/conductores-operativos")
    public ResponseEntity<?> conductoresOperativos() {
        return ResponseEntity.ok(service.conductoresOperativos());
    }

    @PutMapping("/estado/{idRelacion}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long idRelacion,
        @RequestBody String estado) {
            return ResponseEntity.ok(
                service.cambiarEstado(idRelacion, estado)
        );
    }
}
