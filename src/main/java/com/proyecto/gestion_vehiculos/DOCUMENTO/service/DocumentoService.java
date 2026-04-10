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
        return repo.save(d);
    }

    public List<Documento> listar() {
        return repo.findAll();
    }

    public Documento actualizar(Long id, Documento d) {
        d.setId(id);
        return repo.save(d);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}