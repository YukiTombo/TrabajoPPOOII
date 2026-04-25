package com.proyecto.gestion_vehiculos.PERSONA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.gestion_vehiculos.PERSONA.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query(value = """
        SELECT tipo_persona AS tipo, COUNT(*) AS total
        FROM persona
        GROUP BY tipo_persona
        """, nativeQuery = true)
    List<Object[]> totalPorTipo();

}
