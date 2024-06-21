package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.TurnoEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.TurnoSalidaDTO;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.entity.Turno;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.repository.TurnoRepository;
import com.backend.clinicaodontologica.service.ITurnoService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public TurnoSalidaDTO registrarTurno(TurnoEntradaDTO turnoEntradaDTO) throws BadRequestException {

        LOGGER.info("TurnoEntradaDTO: {}", JsonPrinter.toString(turnoEntradaDTO));
        // Fetch the Paciente and Odontologo entities from the database
        Long pacienteId = turnoEntradaDTO.getPaciente().getId();
        Long odontologoId = turnoEntradaDTO.getOdontologo().getId();

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new BadRequestException("Paciente no encontrado"));
        Odontologo odontologo = odontologoRepository.findById(odontologoId)
                .orElseThrow(() -> new BadRequestException("Odontologo no encontrado"));

        // Map to Turno entity and set the fetched entities
        Turno turno = modelMapper.map(turnoEntradaDTO, Turno.class);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        LOGGER.info("TurnoEntidad: {}", JsonPrinter.toString(turno));

        // Save and map to TurnoSalidaDTO
        TurnoSalidaDTO turnoSalidaDTO = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDTO.class);

        LOGGER.info("TurnoSalidaDTO: {}", JsonPrinter.toString(turnoSalidaDTO));

        return turnoSalidaDTO;
    }


    @Override
    public List<TurnoSalidaDTO> listarTurnos() {
        List<TurnoSalidaDTO> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDTO.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public TurnoSalidaDTO buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDTO turnoEncontrado = null;
        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDTO.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }
        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            throw  new ResourceNotFoundException("No se pudo eliminar el turno porque no se encontró un turno con id " + id);
        }
    }

    @Override
    public TurnoSalidaDTO actualizarTurno(TurnoEntradaDTO turnoEntradaDTO, Long id ) throws ResourceNotFoundException {
        Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDTO turnoSalidaDTO = null;
        if (turnoAActualizar != null) {
            modelMapper.map(turnoEntradaDTO, turnoAActualizar);
            turnoAActualizar.setId(id);
            turnoSalidaDTO = modelMapper.map(turnoRepository.save(turnoAActualizar), TurnoSalidaDTO.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDTO));
        } else {
            throw new ResourceNotFoundException("No fue posible actualizar el turno porque el turno con id" + id + " no existe");
        }
        return turnoSalidaDTO;
    }
}
