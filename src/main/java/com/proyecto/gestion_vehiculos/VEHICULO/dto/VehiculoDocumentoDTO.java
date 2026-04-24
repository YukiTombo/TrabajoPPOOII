package com.proyecto.gestion_vehiculos.VEHICULO.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoDocumentoDTO {

    private Long documentoId;
    private String archivoBase64;
    private LocalDate fechaExpedicion;
    private LocalDate fechaVencimiento;

    // getters y setters
    

}
