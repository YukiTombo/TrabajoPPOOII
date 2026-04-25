package com.proyecto.gestion_vehiculos.VEHICULO.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoDocumento;

public interface VehiculoDocumentoRepository extends JpaRepository<VehiculoDocumento, Long>{

    Optional<VehiculoDocumento> findByVehiculo_IdAndDocumento_Id(Long vehiculoId, Long documentoId);
    List<VehiculoDocumento> findByVehiculoId(Long vehiculoId);

    @Query(value = """
        SELECT * FROM vehiculo_documento
        WHERE fecha_vencimiento BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL :dias DAY)
        """, nativeQuery = true)
        List<VehiculoDocumento> porVencer(@Param("dias") int dias);
}
