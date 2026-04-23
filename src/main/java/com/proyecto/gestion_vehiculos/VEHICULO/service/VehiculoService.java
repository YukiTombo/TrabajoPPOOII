package com.proyecto.gestion_vehiculos.VEHICULO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestion_vehiculos.DOCUMENTO.entity.Documento;
import com.proyecto.gestion_vehiculos.DOCUMENTO.enums.TipoVehiculoAplica;
import com.proyecto.gestion_vehiculos.DOCUMENTO.repository.DocumentoRepository;
import com.proyecto.gestion_vehiculos.VEHICULO.enums.EstadoDocumento;
import com.proyecto.gestion_vehiculos.VEHICULO.model.Vehiculo;
import com.proyecto.gestion_vehiculos.VEHICULO.model.VehiculoDocumento;
import com.proyecto.gestion_vehiculos.VEHICULO.repository.VehiculoDocumentoRepository;
import com.proyecto.gestion_vehiculos.VEHICULO.repository.VehiculoRepository;

@Service
public class VehiculoService {

    //INYECCIONES RELACION
    @Autowired
    private VehiculoDocumentoRepository vehiculoDocumentoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;


    @Autowired
    private VehiculoRepository repository;


    public List<VehiculoDocumento> obtenerDocumentosPorVehiculo(Long vehiculoId) {
        return vehiculoDocumentoRepository.findByVehiculoId(vehiculoId);
    }
    
    public Vehiculo guardar(Vehiculo vehiculo) {

        if (repository.findByPlaca(vehiculo.getPlaca()).isPresent()) {
            throw new RuntimeException("La placa ya existe");
        }

        validarPlaca(vehiculo);

        return repository.save(vehiculo);
    }

    public List<Vehiculo> listar() {
        return repository.findAll();
    }

    private void validarPlaca(Vehiculo v) {

        String placa = v.getPlaca();

        if (placa.length() != 6) {
            throw new RuntimeException("La placa debe tener 6 caracteres");
        }

        if (v.getTipoVehiculo().equalsIgnoreCase("Automovil")) {
            if (!placa.matches("^[A-Za-z]{3}[0-9]{3}$")) {
                throw new RuntimeException("Formato inválido para automóvil (ABC123)");
            }
        }

        if (v.getTipoVehiculo().equalsIgnoreCase("Motocicleta")) {
            if (!placa.matches("^[A-Za-z]{3}[0-9]{2}[A-Za-z]{1}$")) {
                throw new RuntimeException("Formato inválido para moto (ABC12A)");
            }
        }
    }

    public Vehiculo actualizar(Long id, Vehiculo vehiculo) {

        Vehiculo existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        repository.findByPlaca(vehiculo.getPlaca())
            .ifPresent(v -> {
                    if (!v.getId().equals(id)) {
                        throw new RuntimeException("La placa ya existe");
                    }
                });        
    
        validarPlaca(vehiculo);
    
        existente.setTipoVehiculo(vehiculo.getTipoVehiculo());
        existente.setPlaca(vehiculo.getPlaca());
        existente.setTipoServicio(vehiculo.getTipoServicio());
        existente.setTipoCombustible(vehiculo.getTipoCombustible());
        existente.setCapacidadPasajeros(vehiculo.getCapacidadPasajeros());
        existente.setColor(vehiculo.getColor());
        existente.setModelo(vehiculo.getModelo());
        existente.setMarca(vehiculo.getMarca());
        existente.setLinea(vehiculo.getLinea());
    
        return repository.save(existente);
    }
    
    public void eliminar(Long id) {
    
        Vehiculo existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    
        repository.delete(existente);
    }
    
    public Vehiculo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    // GUARDAR CON DOCUMENTO
    public Vehiculo guardarConDocumentos(Vehiculo vehiculo, List<Long> documentosIds) {

    if (documentosIds == null || documentosIds.isEmpty()) {
        throw new RuntimeException("Debe tener al menos un documento");
    }

    //VALIDAR SI LA PLACA EXISTE
    if (repository.findByPlaca(vehiculo.getPlaca()).isPresent()) {
        throw new RuntimeException("La placa ya existe");
    }
    validarPlaca(vehiculo);

    Vehiculo vGuardado = repository.save(vehiculo);

    for (Long docId : documentosIds) {

        Documento documento = documentoRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("Documento no existe"));

        //DOCUMENTOS QUE NO APLICAN
        if (vehiculo.getTipoVehiculo().equalsIgnoreCase("Automovil") &&
        documento.getTipoVehiculoAplica() == TipoVehiculoAplica.M) {
        throw new RuntimeException("Documento no aplica a automóvil");
    }

        if (vehiculo.getTipoVehiculo().equalsIgnoreCase("Motocicleta") &&
        documento.getTipoVehiculoAplica() == TipoVehiculoAplica.A) {
        throw new RuntimeException("Documento no aplica a motocicleta");
    }


        VehiculoDocumento vd = new VehiculoDocumento();
        vd.setVehiculo(vGuardado);
        vd.setDocumento(documento);

        vd.setFechaExpedicion(java.time.LocalDate.now());
        vd.setFechaVencimiento(java.time.LocalDate.now().plusYears(1));
        if (vd.getFechaExpedicion().isAfter(vd.getFechaVencimiento())) {
            throw new RuntimeException("Fecha de expedición no puede ser mayor a vencimiento");
        }

        // REGLA DEL PROYECTO
        vd.setEstado(EstadoDocumento.EN_VERIFICACION);

        vehiculoDocumentoRepository.save(vd);
    }

    return vGuardado;    
}

    // Buscar por placa
    public Vehiculo buscarPorPlaca(String placa) {
        return repository.findByPlaca(placa)
            .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    // Buscar por tipo
    public List<Vehiculo> buscarPorTipo(String tipo) {
        return repository.findByTipoVehiculoIgnoreCase(tipo);
    }

    // Buscar por documento
    public List<Vehiculo> buscarPorDocumento(String nombre) {
        return repository.findByDocumentoNombre(nombre);
    }

    // Buscar por estado del documento
    public List<Vehiculo> buscarPorEstadoDocumento(String estado) {

        EstadoDocumento estadoEnum;
    
        try {
            estadoEnum = EstadoDocumento.valueOf(estado.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado inválido");
        }
    
        return repository.findByEstadoDocumento(estadoEnum);
    }


}
