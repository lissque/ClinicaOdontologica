package com.backend.clinicaodontologica.dto.salida;

import java.time.LocalDate;

public class TurnoSalidaDTO {

    private Long id;
    private LocalDate fechaHora;
    private PacienteSalidaDTO paciente;
    private OdontologoSalidaDTO odontologo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
