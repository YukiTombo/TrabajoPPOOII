package com.proyecto.gestion_vehiculos.TRAYECTO.entity;

import com.proyecto.gestion_vehiculos.PERSONA.entity.Persona;
import com.proyecto.gestion_vehiculos.VEHICULO.model.Vehiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trayectos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trayecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrayecto;

    @ManyToOne
    @JoinColumn(name = "idpersona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "idvehiculo")
    private Vehiculo vehiculo;

    @Column(nullable = false)
    private String codigoRuta;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Integer ordenParada;

    private Double latitud;

    private Double longitud;

    private String loginUsuario;

}
