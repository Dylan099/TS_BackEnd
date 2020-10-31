package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "recomendacion", schema = "tds", catalog = "")
public class RecomendacionEntity {
    private int idRecomendacion;
    private String recomendacion;
    private int idPaciente;

    @Id
    @Column(name = "id_recomendacion")
    public int getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    @Basic
    @Column(name = "recomendacion")
    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    @Basic
    @Column(name = "id_paciente")
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecomendacionEntity that = (RecomendacionEntity) o;

        if (idRecomendacion != that.idRecomendacion) return false;
        if (idPaciente != that.idPaciente) return false;
        if (recomendacion != null ? !recomendacion.equals(that.recomendacion) : that.recomendacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecomendacion;
        result = 31 * result + (recomendacion != null ? recomendacion.hashCode() : 0);
        result = 31 * result + idPaciente;
        return result;
    }
}
