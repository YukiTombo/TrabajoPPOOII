package com.proyecto.gestion_vehiculos.VEHICULO.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoPersona;

public interface VehiculoPersonaRepository extends JpaRepository<VehiculoPersona, Long> {

    Optional<VehiculoPersona> findByVehiculoIdAndPersonaId(Long vehiculoId, Long personaId);

    List<VehiculoPersona> findByVehiculoId(Long vehiculoId);

    List<VehiculoPersona> findByEstado(String estado);

}