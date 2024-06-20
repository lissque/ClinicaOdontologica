package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.DomicilioEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    @Test
    @Order(1)
    void registrarOdontologo() {
        OdontologoEntradaDTO odontologoEntradaDTO = new OdontologoEntradaDTO("1234568", "MARIO", "JIMENEZ");
        OdontologoSalidaDTO odontologoGuardado = odontologoService.registrarOdontologo(odontologoEntradaDTO);
        assertNotNull(odontologoGuardado);
    }

    @Test
    @Order(2)
    void listarOdontologos() {
        List<OdontologoSalidaDTO> odontologos = odontologoService.listarOdontologos();
        assertNotNull(odontologos);
    }

    @Test
    @Order(3)
    void buscarOdontologoPorId() throws ResourceNotFoundException {
        OdontologoSalidaDTO odontologoEncontrado = odontologoService.buscarOdontologoPorId(1L);
        assertNotNull(odontologoEncontrado);
        assertEquals(1, odontologoEncontrado.getId());
    }
}