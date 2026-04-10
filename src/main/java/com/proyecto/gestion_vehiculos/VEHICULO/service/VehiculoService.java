package com.proyecto.gestion_vehiculos.VEHICULO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestion_vehiculos.VEHICULO.model.Vehiculo;
import com.proyecto.gestion_vehiculos.VEHICULO.repository.VehiculoRepository;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository repository;

    public Vehiculo guardar(Vehiculo vehiculo) {

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


}
