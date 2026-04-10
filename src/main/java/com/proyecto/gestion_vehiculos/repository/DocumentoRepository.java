package com.proyecto.gestion_vehiculos.repository;

import com.proyecto.gestion_vehiculos.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}