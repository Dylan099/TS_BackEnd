package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "doctor", schema = "tds", catalog = "")
public class DoctorEntity {
    private int idDoctor;
    private String firstName;
    private String lastName;
    private String ci;
    private String correo;
    private String username;
    private String pass;
    private int estatus;
    private int dobleAuth;
    private String telNum;
    private String lastCod;

    @Id
    @Column(name = "id_doctor")
    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "ci")
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    @Basic
    @Column(name = "correo")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "estatus")
    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Basic
    @Column(name = "doble_auth")
    public int getDobleAuth() {
        return dobleAuth;
    }

    public void setDobleAuth(int dobleAuth) {
        this.dobleAuth = dobleAuth;
    }

    @Basic
    @Column(name = "tel_num")
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Basic
    @Column(name = "last_cod")
    public String getLastCod() {
        return lastCod;
    }

    public void setLastCod(String lastCod) {
        this.lastCod = lastCod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorEntity that = (DoctorEntity) o;

        if (idDoctor != that.idDoctor) return false;
        if (estatus != that.estatus) return false;
        if (dobleAuth != that.dobleAuth) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (ci != null ? !ci.equals(that.ci) : that.ci != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (telNum != null ? !telNum.equals(that.telNum) : that.telNum != null) return false;
        if (lastCod != null ? !lastCod.equals(that.lastCod) : that.lastCod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDoctor;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ci != null ? ci.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + estatus;
        result = 31 * result + dobleAuth;
        result = 31 * result + (telNum != null ? telNum.hashCode() : 0);
        result = 31 * result + (lastCod != null ? lastCod.hashCode() : 0);
        return result;
    }
}
