package com.backend.clinicaodontologica.dto.entrada;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDTO;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDTO;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TurnoEntradaDTO {

    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    private LocalDate fechaHora;
    @NotNull(message = "Debe seleccionarse un paciente")
    private PacienteSalidaDTO paciente;
    @NotNull(message = "Debe seleccionarse un odontologo")
    private OdontologoSalidaDTO odontologo;

    public TurnoEntradaDTO(LocalDate fechaHora, PacienteSalidaDTO paciente, OdontologoSalidaDTO odontologo) {
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    public PacienteSalidaDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteSalidaDTO paciente) {
        this.paciente = paciente;
    }

    public OdontologoSalidaDTO getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoSalidaDTO odontologo) {
        this.odontologo = odontologo;
    }
}
