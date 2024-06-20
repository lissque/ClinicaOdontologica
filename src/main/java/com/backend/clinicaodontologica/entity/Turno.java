package com.backend.clinicaodontologica.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TURNOS")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private LocalDate fechaHora;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Odontologo odontologo;

    public Turno() { }

    public Turno(Long id, LocalDate fechaHora, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}
