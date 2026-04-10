package com.proyecto.gestion_vehiculos.VEHICULO.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion_vehiculos.VEHICULO.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{

    Optional<Vehiculo> findByPlaca(String placa);

}
