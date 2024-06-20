package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IOdontologoService {

    OdontologoSalidaDTO registrarOdontologo(OdontologoEntradaDTO odontologoEntradaDTO);
    List<OdontologoSalidaDTO> listarOdontologos();
    OdontologoSalidaDTO buscarOdontologoPorId(Long id) throws ResourceNotFoundException;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException; //throws ResourceNotFoundException;
    OdontologoSalidaDTO actualizarOdontologo(OdontologoEntradaDTO odontologoEntradaDTO, Long id) throws ResourceNotFoundException;

}
