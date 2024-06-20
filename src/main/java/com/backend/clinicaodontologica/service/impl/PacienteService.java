package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.service.IPacienteService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDTO registrarPaciente(PacienteEntradaDTO pacienteEntradaDTO) {

        LOGGER.info("PacienteEntradaDTO: " + JsonPrinter.toString(pacienteEntradaDTO));
        Paciente paciente = modelMapper.map(pacienteEntradaDTO, Paciente.class);
        LOGGER.info("PacienteEntidad: " + JsonPrinter.toString(paciente));

        PacienteSalidaDTO pacienteSalidaDTO = modelMapper.map(pacienteRepository.save(paciente), PacienteSalidaDTO.class);
        LOGGER.info("PacienteSalidaDto: " + JsonPrinter.toString(pacienteSalidaDTO));
        return pacienteSalidaDTO;
    }

    @Override
    public List<PacienteSalidaDTO> listarPacientes() {
        List<PacienteSalidaDTO> pacientes = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDTO.class))
                .toList();
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientes));
        return pacientes;
    }

    @Override
    public PacienteSalidaDTO buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDTO pacienteEncontrado = null;

        if (pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDTO.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
        }

        return pacienteEncontrado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPacientePorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id {}", id);
        }  else {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }
    }

    @Override
    public PacienteSalidaDTO actualizarPaciente(PacienteEntradaDTO pacienteEntradaDto, Long id) throws ResourceNotFoundException {

        Paciente pacienteRecibido = modelMapper.map(pacienteEntradaDto, Paciente.class);
        Paciente pacienteAActualizar = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDTO pacienteSalidaDto = null;

        if(pacienteAActualizar != null){

            pacienteRecibido.setId(pacienteAActualizar.getId());
            pacienteRecibido.getDomicilio().setId(pacienteAActualizar.getDomicilio().getId());
            pacienteAActualizar = pacienteRecibido;
            pacienteRepository.save(pacienteAActualizar);
            pacienteSalidaDto = modelMapper.map(pacienteAActualizar, PacienteSalidaDTO.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));

        } else {
            throw new ResourceNotFoundException("No fue posible actualizar el paciente porque el paciente " + id + " no existe.");
        }

        return pacienteSalidaDto;
    }

    /**
     * Configuracion del mapper para asignar el valor del atributo Domicilio para personalizar
     * cómo los objetos se convierten entre sí, específicamente entre PacienteEntradaDto y
     * Paciente, y entre Paciente y PacienteSalidaDto.
     */
    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDTO.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDTO::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDTO.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDTO::setDomicilio));
    }
}
