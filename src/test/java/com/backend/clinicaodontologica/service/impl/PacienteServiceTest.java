package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.DomicilioEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
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
class PacienteServiceTest {

    @Autowired
    private IPacienteService pacienteService;

    @Test
    @Order(1)
    void registrarPaciente() {
        DomicilioEntradaDTO domicilioEntradaDTO = new DomicilioEntradaDTO("Avenida", 12, "Sur", "Quindio");
        PacienteEntradaDTO pacienteEntradaDTO = new PacienteEntradaDTO("Lissette", "Quebrada", "123456", LocalDate.now(), domicilioEntradaDTO);
        PacienteSalidaDTO pacienteGuardado = pacienteService.registrarPaciente(pacienteEntradaDTO);
        assertNotNull(pacienteGuardado);
    }

    @Test
    @Order(2)
    void listarPacientes() {
        List<PacienteSalidaDTO> pacientes = pacienteService.listarPacientes();
        assertNotNull(pacientes);
    }

    @Test
    void buscarPacientePorId() throws ResourceNotFoundException {
        PacienteSalidaDTO pacienteEncontrado = pacienteService.buscarPacientePorId(1L);
        assertNotNull(pacienteEncontrado);
        assertEquals(1, pacienteEncontrado.getId());
    }
}