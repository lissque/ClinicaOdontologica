package com.backend.clinicaodontologica.dto.salida;

import com.backend.clinicaodontologica.dto.entrada.DomicilioEntradaDTO;

import java.time.LocalDate;

public class PacienteSalidaDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioSalidaDTO domicilio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DomicilioSalidaDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioSalidaDTO domicilio) {
        this.domicilio = domicilio;
    }
}
