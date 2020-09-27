package com.example.demo.dto;

public class DoctorDto {
    private String firstName;
    private String lastName;
    private String ci;
    private String correo;
    private String user;
    private String pass;

    public DoctorDto(String firstName, String lastName, String ci, String correo, String user, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ci = ci;
        this.correo = correo;
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

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
