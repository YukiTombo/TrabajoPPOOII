package com.proyecto.gestion_vehiculos.DOCUMENTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion_vehiculos.DOCUMENTO.entity.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}