package com.proyecto.gestion_vehiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionVehiculosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionVehiculosApplication.class, args);
	}

}
