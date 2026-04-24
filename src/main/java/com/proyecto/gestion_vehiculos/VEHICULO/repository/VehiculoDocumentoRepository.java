package com.proyecto.gestion_vehiculos.VEHICULO.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoDocumento;

public interface VehiculoDocumentoRepository extends JpaRepository<VehiculoDocumento, Long>{

    Optional<VehiculoDocumento> findByVehiculo_IdAndDocumento_Id(Long vehiculoId, Long documentoId);
    List<VehiculoDocumento> findByVehiculoId(Long vehiculoId);
}
