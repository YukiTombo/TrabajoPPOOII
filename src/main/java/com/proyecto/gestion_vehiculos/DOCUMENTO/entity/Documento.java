package com.proyecto.gestion_vehiculos.DOCUMENTO.entity;

import com.proyecto.gestion_vehiculos.DOCUMENTO.enums.TipoObligatorio;
import com.proyecto.gestion_vehiculos.DOCUMENTO.enums.TipoVehiculoAplica;

import jakarta.persistence.*;

@Entity
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoVehiculoAplica tipoVehiculoAplica;

    @Enumerated(EnumType.STRING)
    private TipoObligatorio obligatorio;

    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoVehiculoAplica getTipoVehiculoAplica() {
        return tipoVehiculoAplica;
    }

    public void setTipoVehiculoAplica(TipoVehiculoAplica tipoVehiculoAplica) {
        this.tipoVehiculoAplica = tipoVehiculoAplica;
    }

    public TipoObligatorio getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(TipoObligatorio obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}