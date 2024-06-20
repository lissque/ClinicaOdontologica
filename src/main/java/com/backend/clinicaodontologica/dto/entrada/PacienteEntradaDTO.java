package com.backend.clinicaodontologica.dto.entrada;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PacienteEntradaDTO {

    @NotBlank(message = "Debe especificarse el nombre del paciente")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    private String apellido;
    @NotBlank(message = "Debe especificarse el dni del paciente")
    private String dni;
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;
    @NotNull(message = "Debe especificarse el domicilio del paciente")
    private DomicilioEntradaDTO domicilio;

    public PacienteEntradaDTO(String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioEntradaDTO domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioEntradaDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntradaDTO domicilio) {
        this.domicilio = domicilio;
    }
}



