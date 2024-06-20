package com.backend.clinicaodontologica.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class OdontologoEntradaDTO {

    @NotBlank(message = "Debe especificarse la matricula del odontologo")
    @Size(max = 50, message = "La matricula no puede tener mas de 50 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "La matricula solo puede contener numeros")
    private String matricula;
    @NotNull(message = "El nombre del odontologo no puede ser nulo")
    @Size(max = 50, message = "El nombre del odontologo no puede tener mas de 50 caracteres")
    private String nombre;
    @NotNull(message = "El apellido del odontologo no puede ser nulo")
    @Size(max = 50, message = "El apellido del odontologo no puede tener mas de 50 caracteres")
    private String apellido;

    public OdontologoEntradaDTO(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
}
