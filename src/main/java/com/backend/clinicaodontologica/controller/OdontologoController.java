package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDTO;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IOdontologoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    @Autowired
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDTO> registrarOdontologo (@Valid @RequestBody OdontologoEntradaDTO odontologoEntradaDTO) {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologoEntradaDTO), HttpStatus.CREATED);
    }

    @RequestMapping("/listar")
    public ResponseEntity<List<OdontologoSalidaDTO>> listarOdontologos() {
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<OdontologoSalidaDTO> buscarOdontologoPorId (@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OdontologoSalidaDTO> actualizarOdontologo (@Valid @RequestBody OdontologoEntradaDTO odontologoEntradaDTO, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologoEntradaDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo (@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
