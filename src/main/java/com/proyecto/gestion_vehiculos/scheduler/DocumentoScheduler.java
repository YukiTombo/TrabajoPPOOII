package com.proyecto.gestion_vehiculos.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.gestion_vehiculos.VEHICULO.enums.EstadoDocumento;
import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoDocumento;
import com.proyecto.gestion_vehiculos.VEHICULO.repository.VehiculoDocumentoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentoScheduler {

        private final VehiculoDocumentoRepository repository;

    @Scheduled(fixedRate = 120000)
    public void verificarDocumentos(){

        List<VehiculoDocumento> documentos =
                repository.findAll();

        for(VehiculoDocumento documento : documentos){

            if(documento.getFechaVencimiento() != null
                    &&
               documento.getFechaVencimiento()
                       .isBefore(LocalDate.now())){

                documento.setEstado(
                        EstadoDocumento.VENCIDO
                );

                repository.save(documento);

                System.out.println(
                        "Documento vencido actualizado"
                );
            }
        }
    }


}
