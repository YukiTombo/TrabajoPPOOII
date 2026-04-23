package com.proyecto.gestion_vehiculos.DOCUMENTO.service;

import com.proyecto.gestion_vehiculos.DOCUMENTO.entity.Documento;
import com.proyecto.gestion_vehiculos.DOCUMENTO.repository.DocumentoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository repo;

    public DocumentoService(DocumentoRepository repo) {
        this.repo = repo;
    }

    public Documento guardar(Documento d) {

        if (d.getCodigo() == null || d.getCodigo().trim().isEmpty()) {
            throw new RuntimeException("Código obligatorio");
        }
    
        if (d.getTipoVehiculoAplica() == null) {
            throw new RuntimeException("Tipo de vehículo obligatorio");
        }
    
        // VALIDAR DUPLICADO
        if (repo.findAll().stream()
                .anyMatch(doc -> doc.getCodigo().equalsIgnoreCase(d.getCodigo()))) {
            throw new RuntimeException("El código ya existe");
        }
    
        return repo.save(d);
    }

    public List<Documento> listar() {
        return repo.findAll();
    }

    public Documento actualizar(Long id, Documento d) {

        Documento existente = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Documento no encontrado"));
    
        existente.setCodigo(d.getCodigo());
        existente.setNombre(d.getNombre());
        existente.setTipoVehiculoAplica(d.getTipoVehiculoAplica());
        existente.setObligatorio(d.getObligatorio());
        existente.setDescripcion(d.getDescripcion());
    
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}