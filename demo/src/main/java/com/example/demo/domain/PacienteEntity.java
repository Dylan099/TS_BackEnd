package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "paciente", schema = "tds", catalog = "")
public class PacienteEntity {
    private int idPaciente;
    private String firstName;
    private String lastName;
    private String ci;
    private String sexo;
    private String edad;
    private Integer idStatus;
    private String correo;
    private String username;
    private String pass;
    private Integer idDoctor;
    private int numRecomendacion;
    private int estatus;

    @Id
    @Column(name = "id_paciente")
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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
    @Column(name = "sexo")
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Basic
    @Column(name = "edad")
    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Basic
    @Column(name = "id_status")
    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
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
    @Column(name = "id_doctor")
    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Basic
    @Column(name = "num_recomendacion")
    public int getNumRecomendacion() {
        return numRecomendacion;
    }

    public void setNumRecomendacion(int numRecomendacion) {
        this.numRecomendacion = numRecomendacion;
    }

    @Basic
    @Column(name = "estatus")
    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacienteEntity that = (PacienteEntity) o;

        if (idPaciente != that.idPaciente) return false;
        if (numRecomendacion != that.numRecomendacion) return false;
        if (estatus != that.estatus) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (ci != null ? !ci.equals(that.ci) : that.ci != null) return false;
        if (sexo != null ? !sexo.equals(that.sexo) : that.sexo != null) return false;
        if (edad != null ? !edad.equals(that.edad) : that.edad != null) return false;
        if (idStatus != null ? !idStatus.equals(that.idStatus) : that.idStatus != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (idDoctor != null ? !idDoctor.equals(that.idDoctor) : that.idDoctor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPaciente;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ci != null ? ci.hashCode() : 0);
        result = 31 * result + (sexo != null ? sexo.hashCode() : 0);
        result = 31 * result + (edad != null ? edad.hashCode() : 0);
        result = 31 * result + (idStatus != null ? idStatus.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (idDoctor != null ? idDoctor.hashCode() : 0);
        result = 31 * result + numRecomendacion;
        result = 31 * result + estatus;
        return result;
    }
}
