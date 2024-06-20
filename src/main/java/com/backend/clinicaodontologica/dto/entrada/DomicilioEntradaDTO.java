package com.backend.clinicaodontologica.dto.entrada;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class DomicilioEntradaDTO {

    @NotBlank(message = "Debe especificarse la calle")
    private String calle;
    @Positive(message = "El numero del domicilio no puede ser nulo o menor a 1")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private int numero;
    @NotBlank(message = "Debe especificarse la localidad")
    private String localidad;
    @NotBlank(message = "Debe especificarse la provincia")
    private String provincia;

    public DomicilioEntradaDTO(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
