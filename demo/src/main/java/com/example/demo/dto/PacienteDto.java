package com.example.demo.dto;

public class PacienteDto {
    private String firstName;
    private String lastName;
    private String ci;
    private String correo;
    private String lastStatus;
    private int idDoctor;
    private String user;
    private String pass;

    public PacienteDto(String firstName, String lastName, String ci, String correo, String lastStatus, int idDoctor, String user, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ci = ci;
        this.correo = correo;
        this.lastStatus = lastStatus;
        this.idDoctor = idDoctor;
        this.user = user;
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCi() {
        return ci;
    }

    public String getCorreo() {
        return correo;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
