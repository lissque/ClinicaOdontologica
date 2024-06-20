package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    public PacienteController (IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDTO> registrarPaciente ( @Valid @RequestBody PacienteEntradaDTO pacienteEntradaDTO) {
        return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteEntradaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteSalidaDTO>> listarPacientes () {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteSalidaDTO> buscarPacientePorId (@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteSalidaDTO> actualizarPaciente (@Valid @RequestBody PacienteEntradaDTO pacienteEntradaDto, Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.actualizarPaciente(pacienteEntradaDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPaciente (@RequestParam Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
