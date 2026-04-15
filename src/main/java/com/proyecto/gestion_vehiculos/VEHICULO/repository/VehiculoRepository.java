package com.proyecto.gestion_vehiculos.VEHICULO.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.gestion_vehiculos.VEHICULO.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{

    Optional<Vehiculo> findByPlaca(String placa);
    // Buscar por tipo de vehículo
    List<Vehiculo> findByTipoVehiculoIgnoreCase(String tipoVehiculo);

    // Buscar por nombre de documento
    @Query("SELECT v FROM Vehiculo v JOIN v.documentos vd JOIN vd.documento d WHERE LOWER(d.nombre) = LOWER(:nombre)")
    List<Vehiculo> findByDocumentoNombre(@Param("nombre") String nombre);

    // Buscar por estado del documento
    @Query("SELECT v FROM Vehiculo v JOIN v.documentos vd WHERE vd.estado = :estado")
    List<Vehiculo> findByEstadoDocumento(@Param("estado") String estado);

}
