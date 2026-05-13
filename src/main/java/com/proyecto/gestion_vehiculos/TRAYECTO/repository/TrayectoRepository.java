package com.proyecto.gestion_vehiculos.TRAYECTO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion_vehiculos.TRAYECTO.entity.Trayecto;

public interface TrayectoRepository extends JpaRepository<Trayecto, Long>{
    List<Trayecto>
    findByCodigoRutaOrderByOrdenParadaAsc(
            String codigoRuta
    );

    List<Trayecto>
    findByPersonaIdentificacion(
            String identificacion
    );

    List<Trayecto>
    findByVehiculoPlaca(
            String placa
    );

    List<Trayecto>
    findByLatitudIsNullOrLongitudIsNull();

}
