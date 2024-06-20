package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDTO;
import com.backend.clinicaodontologica.dto.entrada.TurnoEntradaDTO;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDTO;
import com.backend.clinicaodontologica.dto.salida.TurnoSalidaDTO;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.ITurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turnos")
public class TurnoController {

    @Autowired
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDTO> registrarTurno (@Valid @RequestBody TurnoEntradaDTO turnoEntradaDTO) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDTO), HttpStatus.CREATED);
    }

    @PostMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDTO>> listarTurnos () {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoSalidaDTO> buscarTurnoPorId (@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoSalidaDTO> actualizarTurno (@Valid @RequestBody TurnoEntradaDTO turnoEntradaDTO, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.actualizarTurno(turnoEntradaDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarTurno (@RequestParam Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
