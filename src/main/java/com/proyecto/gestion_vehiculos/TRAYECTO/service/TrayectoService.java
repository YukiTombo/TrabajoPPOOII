package com.proyecto.gestion_vehiculos.TRAYECTO.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.gestion_vehiculos.TRAYECTO.dto.TrayectoVehiculoDTO;
import com.proyecto.gestion_vehiculos.TRAYECTO.entity.Trayecto;
import com.proyecto.gestion_vehiculos.TRAYECTO.repository.TrayectoRepository;
import com.proyecto.gestion_vehiculos.VEHICULO.enums.EstadoDocumento;
import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoDocumento;
import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoPersona;
import com.proyecto.gestion_vehiculos.VEHICULO.repository.VehiculoDocumentoRepository;
import com.proyecto.gestion_vehiculos.VEHICULO.repository.VehiculoPersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrayectoService {


    private final TrayectoRepository trayectoRepository;

    private final VehiculoPersonaRepository vehiculoPersonaRepository;

    private final VehiculoDocumentoRepository vehiculoDocumentoRepository;

    public Trayecto crear(Trayecto trayecto){

        VehiculoPersona relacion =
                vehiculoPersonaRepository
                        .findByVehiculoIdAndPersonaId(
                                trayecto.getVehiculo().getId(),
                                trayecto.getPersona().getId()
                
                )

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Relación no encontrada"
                                )
                        );

        if(!relacion.getEstado().equals("PO")){
            throw new RuntimeException(
                    "Conductor restringido"
            );
        }

        List<VehiculoDocumento> docs =
                vehiculoDocumentoRepository
                        .findByVehiculo(
                                trayecto.getVehiculo()
                        );

        boolean vencido =
                docs.stream()
                        .anyMatch(
                                d -> d.getEstado()
                                        == EstadoDocumento.VENCIDO
                        );

        if(vencido){
            throw new RuntimeException(
                    "Vehículo con documentos vencidos"
            );
        }

        return trayectoRepository.save(trayecto);
    }

    public List<Trayecto> consultarRuta(
            String codigoRuta
    ){
        return trayectoRepository
                .findByCodigoRutaOrderByOrdenParadaAsc(
                        codigoRuta
                );
    }

    public List<String> consultarRutasConductor(
            String identificacion
    ){

        return trayectoRepository
                .findByPersonaIdentificacion(
                        identificacion
                )
                .stream()
                .map(Trayecto::getCodigoRuta)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<TrayectoVehiculoDTO> consultarVehiculo(String placa){
        return trayectoRepository
                .findByVehiculoPlaca(placa)
                .stream()
                .map(t -> new TrayectoVehiculoDTO(
                        t.getCodigoRuta(),
                        t.getPersona().getNombres()
                ))
                .distinct()
                .toList();
        }

    
}
