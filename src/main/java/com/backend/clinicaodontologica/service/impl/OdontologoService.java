package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDTO registrarOdontologo(OdontologoEntradaDTO odontologoEntradaDTO) {
        LOGGER.info("OdontologoEntradaDTO: " + JsonPrinter.toString(odontologoEntradaDTO));
        Odontologo odontologo = modelMapper.map(odontologoEntradaDTO, Odontologo.class);
        LOGGER.info("OdontologoEntidad: " + JsonPrinter.toString(odontologo));

        OdontologoSalidaDTO odontologoSalidaDTO = modelMapper.map(odontologoRepository.save(odontologo), OdontologoSalidaDTO.class);
        LOGGER.info("OdontologoSalidaDto: " + JsonPrinter.toString(odontologoSalidaDTO));
        return odontologoSalidaDTO;
    }

    @Override
    public List<OdontologoSalidaDTO> listarOdontologos() {
        List<OdontologoSalidaDTO> odontologos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDTO.class))
                .toList();
        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologos));
        return odontologos;
    }

    @Override
    public OdontologoSalidaDTO buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDTO odontologoEncontrado = null;

        if (odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDTO.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
        }

        return odontologoEncontrado;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + id);
        }
    }

    @Override
    public OdontologoSalidaDTO actualizarOdontologo(OdontologoEntradaDTO odontologoEntradaDTO, Long id) throws ResourceNotFoundException {

        Odontologo odontologoRecibido = modelMapper.map(odontologoEntradaDTO, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDTO odontologoSalidaDTO = null;

        if(odontologoAActualizar != null){

            odontologoRecibido.setId(odontologoAActualizar.getId());
            odontologoAActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoAActualizar);
            odontologoSalidaDTO = modelMapper.map(odontologoAActualizar, OdontologoSalidaDTO.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDTO));

        } else {
            throw new ResourceNotFoundException("No fue posible actualizar el odontologo porque el odontologo " + id +" no existe");
        }

        return odontologoSalidaDTO;
    }

}
