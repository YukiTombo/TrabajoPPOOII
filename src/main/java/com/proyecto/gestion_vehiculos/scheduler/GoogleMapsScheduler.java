package com.proyecto.gestion_vehiculos.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.gestion_vehiculos.APIs.GoogleMaps.Geocoder;
import com.proyecto.gestion_vehiculos.TRAYECTO.entity.Trayecto;
import com.proyecto.gestion_vehiculos.TRAYECTO.repository.TrayectoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleMapsScheduler {

        private final TrayectoRepository trayectoRepository;

    private final Geocoder geocoder;

    @Scheduled(fixedRate = 90000)
    public void actualizarCoordenadas(){

        List<Trayecto> trayectos =
                trayectoRepository
                        .findByLatitudIsNullOrLongitudIsNull();

        for(Trayecto trayecto : trayectos){

            try{

                Double[] coordenadas =
                        geocoder.getLatLng(
                                trayecto.getUbicacion()
                        );

                if(coordenadas != null){

                    trayecto.setLatitud(
                            coordenadas[0]
                    );

                    trayecto.setLongitud(
                            coordenadas[1]
                    );

                    trayectoRepository.save(trayecto);

                    System.out.println(
                            "Coordenadas actualizadas"
                    );
                }

            }catch(Exception e){

                System.out.println(
                        "Error Google Maps: "
                                + e.getMessage()
                );
            }
        }
    }


}
