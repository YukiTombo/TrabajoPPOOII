package com.proyecto.gestion_vehiculos.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.gestion_vehiculos.PERSONA.entity.Persona;
import com.proyecto.gestion_vehiculos.PERSONA.repository.PersonaRepository;
import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoPersona;
import com.proyecto.gestion_vehiculos.VEHICULO.repository.VehiculoPersonaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LicenciaScheduler {

     private final PersonaRepository personaRepository;

    private final VehiculoPersonaRepository
            vehiculoPersonaRepository;

    @Scheduled(fixedRate = 120000)
    public void verificarLicencias(){

        List<Persona> personas =
                personaRepository.findAll();

        for(Persona persona : personas){

            if(persona.getFechaVigenciaLicencia()
                    != null
                    &&
                    persona.getFechaVigenciaLicencia()
                            .isBefore(LocalDate.now())){

                List<VehiculoPersona> relaciones =
                        vehiculoPersonaRepository
                                .findByPersonaId(persona.getId());

                for(VehiculoPersona vp : relaciones){

                    vp.setEstado("RO");

                    vehiculoPersonaRepository.save(vp);
                }
            }
        }
    }

}
