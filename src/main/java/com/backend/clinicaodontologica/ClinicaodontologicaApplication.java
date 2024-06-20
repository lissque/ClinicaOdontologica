package com.backend.clinicaodontologica;

import org.slf4j.Logger;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaodontologicaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaodontologicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClinicaodontologicaApplication.class, args);
		LOGGER.error("Este es un mensaje de debug para verificar la configuración de log4j.");
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}