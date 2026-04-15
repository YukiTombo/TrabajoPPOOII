package com.proyecto.gestion_vehiculos.VEHICULO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoDocumento;

public interface VehiculoDocumentoRepository extends JpaRepository<VehiculoDocumento, Long>{

    List<VehiculoDocumento> findByVehiculoId(Long vehiculoId);
}
