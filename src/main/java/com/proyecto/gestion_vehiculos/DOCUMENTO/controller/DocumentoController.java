package com.proyecto.gestion_vehiculos.DOCUMENTO.controller;

import com.proyecto.gestion_vehiculos.DOCUMENTO.entity.Documento;
import com.proyecto.gestion_vehiculos.DOCUMENTO.service.DocumentoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.proyecto.gestion_vehiculos.response.ApiResponse;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> crear(@RequestBody Documento d) {
        service.guardar(d);
        return ResponseEntity.ok(new ApiResponse("Documento creado con éxito"));
    }

    @GetMapping
    public List<Documento> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizar(@PathVariable Long id, @RequestBody Documento d) {
        service.actualizar(id, d);
        return ResponseEntity.ok(new ApiResponse("Documento actualizado con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ApiResponse("Documento eliminado con éxito"));
    }
}