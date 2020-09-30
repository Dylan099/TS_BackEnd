package com.example.demo.dto;

public class SuscripcionDto {
    private String correoPaciente;
    private String correoDoctor;

    public SuscripcionDto(String correoPaciente, String correoDoctor) {
        this.correoPaciente = correoPaciente;
        this.correoDoctor = correoDoctor;
    }

    public String getCorreoPaciente() {
        return correoPaciente;
    }

    public void setCorreoPaciente(String correoPaciente) {
        this.correoPaciente = correoPaciente;
    }

    public String getCorreoDoctor() {
        return correoDoctor;
    }

    public void setCorreoDoctor(String correoDoctor) {
        this.correoDoctor = correoDoctor;
    }
}
