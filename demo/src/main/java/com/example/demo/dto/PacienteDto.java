package com.example.demo.dto;

public class PacienteDto {
    private int idPaciente;
    private String firstName;
    private String lastName;
    private String ci;
    private String sexo;
    private String edad;
    private String idStatus;
    private String correo;
    private String username;
    private String pass;
    private Integer idDoctor;
    private String telNum;
    private String lastCode;

    public PacienteDto(int idPaciente, String firstName, String lastName, String ci, String sexo, String edad, String idStatus, String correo, String username, String pass, Integer idDoctor, String lastCode,String telNum) {
        this.idPaciente = idPaciente;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ci = ci;
        this.sexo = sexo;
        this.edad = edad;
        this.idStatus = idStatus;
        this.correo = correo;
        this.username = username;
        this.pass = pass;
        this.idDoctor = idDoctor;
        this.telNum = telNum;
        this.lastCode = lastCode;
    }

    public PacienteDto(String firstName, String lastName, String ci, String sexo, String edad, String idStatus, String correo, String username, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ci = ci;
        this.sexo = sexo;
        this.edad = edad;
        this.idStatus = idStatus;
        this.correo = correo;
        this.username = username;
        this.pass = pass;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getLastCode() {
        return lastCode;
    }

    public void setLastCode(String lastCode) {
        this.lastCode = lastCode;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }


}