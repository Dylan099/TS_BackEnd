package com.example.demo.domain;

import javax.persistence.*;
import java.util.Objects;

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

    @Id
    @Column(name = "idDoctor")
    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEntity that = (DoctorEntity) o;
        return idDoctor == that.idDoctor &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(ci, that.ci) &&
                Objects.equals(correo, that.correo) &&
                Objects.equals(username, that.username) &&
                Objects.equals(pass, that.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoctor, firstName, lastName, ci, correo, username, pass);
    }
}
