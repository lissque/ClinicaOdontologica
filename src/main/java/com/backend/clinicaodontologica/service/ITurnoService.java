package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.TurnoEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.TurnoSalidaDTO;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDTO registrarTurno(TurnoEntradaDTO turnoEntradaDTO) throws BadRequestException;
    List<TurnoSalidaDTO> listarTurnos();
    TurnoSalidaDTO buscarTurnoPorId (Long id) throws ResourceNotFoundException;
    void eliminarTurno (Long id) throws ResourceNotFoundException;
    TurnoSalidaDTO actualizarTurno (TurnoEntradaDTO turnoEntradaDTO, Long id) throws ResourceNotFoundException;
}
