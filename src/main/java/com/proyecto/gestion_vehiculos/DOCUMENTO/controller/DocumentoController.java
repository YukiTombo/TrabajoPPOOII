package com.proyecto.gestion_vehiculos.DOCUMENTO.controller;

import com.proyecto.gestion_vehiculos.DOCUMENTO.entity.Documento;
import com.proyecto.gestion_vehiculos.DOCUMENTO.service.DocumentoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @PostMapping
    public Documento crear(@RequestBody Documento d) {
        return service.guardar(d);
    }

    @GetMapping
    public List<Documento> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public Documento actualizar(@PathVariable Long id, @RequestBody Documento d) {
        return service.actualizar(id, d);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}