package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.DomicilioEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.TurnoEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.dto.salida.TurnoSalidaDTO;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.service.IPacienteService;
import com.backend.clinicaodontologica.service.ITurnoService;
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
class TurnoServiceTest {

    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IOdontologoService odontologoService;

    @Test
    @Order(1)
    void registrarTurno() throws BadRequestException {
        // Guardar primero el domicilio, paciente y odontologo
        DomicilioEntradaDTO domicilioEntradaDTO = new DomicilioEntradaDTO("Avenida", 12, "Sur", "Quindio");
        PacienteEntradaDTO pacienteEntradaDTO = new PacienteEntradaDTO("Lissette", "Quebrada", "123456", LocalDate.now(), domicilioEntradaDTO);
        PacienteSalidaDTO pacienteGuardado = pacienteService.registrarPaciente(pacienteEntradaDTO);

        OdontologoEntradaDTO odontologoEntradaDTO = new OdontologoEntradaDTO("1234568", "MARIO", "JIMENEZ");
        OdontologoSalidaDTO odontologoGuardado = odontologoService.registrarOdontologo(odontologoEntradaDTO);

        // Crear el Turno con el Paciente y Odontologo guardados
        TurnoEntradaDTO turnoEntradaDTO = new TurnoEntradaDTO(LocalDate.now(), pacienteGuardado, odontologoGuardado);
        TurnoSalidaDTO turnoGuardado = turnoService.registrarTurno(turnoEntradaDTO);

        assertNotNull(turnoGuardado);
    }

    @Test
    @Order(2)
    void listarTurnos() {
        List<TurnoSalidaDTO> turnos = turnoService.listarTurnos();
        assertNotNull(turnos);
    }

    @Test
    @Order(3)
    void buscarTurnoPorId() throws ResourceNotFoundException {
        TurnoSalidaDTO turnoEncontrado = turnoService.buscarTurnoPorId(1L);
        assertNotNull(turnoEncontrado);
        assertEquals(1, turnoEncontrado.getId());
    }
}