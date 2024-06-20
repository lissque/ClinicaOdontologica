package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPacienteService {

    PacienteSalidaDTO registrarPaciente(PacienteEntradaDTO pacienteEntradaDTO);
    List<PacienteSalidaDTO> listarPacientes();
    PacienteSalidaDTO buscarPacientePorId(Long id) throws ResourceNotFoundException;
    void eliminarPaciente(Long id) throws ResourceNotFoundException; //throws ResourceNotFoundException;
    PacienteSalidaDTO actualizarPaciente(PacienteEntradaDTO pacienteEntradaDTO, Long id) throws ResourceNotFoundException;

}
