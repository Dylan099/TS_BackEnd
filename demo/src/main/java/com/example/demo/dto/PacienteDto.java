package com.example.demo.dto;

public class PacienteDto {
    private String firstName;
    private String lastName;
    private String ci;
    public String correo;
    private String sexo;
    private String edad;
    private int idStatus;
    private int idDoctor;
    private String user;
    private String pass;

    public PacienteDto(String firstName, String lastName, String ci, String correo, String sexo, String edad, int idStatus, int idDoctor, String user, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ci = ci;
        this.correo = correo;
        this.sexo = sexo;
        this.edad = edad;
        this.idStatus = idStatus;
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

    public int getIdDoctor() {
        return idDoctor;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEdad() {
        return edad;
    }

    public int getIdStatus() {
        return idStatus;
    }
}
