package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "estatus", schema = "tds", catalog = "")
public class EstatusEntity {
    private int idStatus;
    private String estatus;

    @Id
    @Column(name = "id_status")
    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    @Basic
    @Column(name = "estatus")
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstatusEntity that = (EstatusEntity) o;

        if (idStatus != that.idStatus) return false;
        if (estatus != null ? !estatus.equals(that.estatus) : that.estatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStatus;
        result = 31 * result + (estatus != null ? estatus.hashCode() : 0);
        return result;
    }
}
