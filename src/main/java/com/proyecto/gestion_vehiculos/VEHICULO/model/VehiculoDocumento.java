package com.proyecto.gestion_vehiculos.VEHICULO.model;

import java.time.LocalDate;

import com.proyecto.gestion_vehiculos.DOCUMENTO.entity.Documento;
import com.proyecto.gestion_vehiculos.VEHICULO.enums.EstadoDocumento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo_documento")
public class VehiculoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "documento_id", nullable = false)
    private Documento documento;

    private LocalDate fechaExpedicion;
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    private EstadoDocumento estado;

    // GETTERS Y SETTERS
    public Long getId() { return id; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Documento getDocumento() { return documento; }
    public void setDocumento(Documento documento) { this.documento = documento; }

    public LocalDate getFechaExpedicion() { return fechaExpedicion; }
    public void setFechaExpedicion(LocalDate fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public EstadoDocumento getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoDocumento estado) {
        this.estado = estado;
    }

}
