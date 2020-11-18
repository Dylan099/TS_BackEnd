package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "hospital", schema = "tds", catalog = "")
public class HospitalEntity {
    private int idHospital;
    private String hospital;
    private double latitud;
    private double longitud;

    @Id
    @Column(name = "id_hospital")
    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    @Basic
    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Basic
    @Column(name = "latitud")
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    @Basic
    @Column(name = "longitud")
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HospitalEntity that = (HospitalEntity) o;

        if (idHospital != that.idHospital) return false;
        if (Double.compare(that.latitud, latitud) != 0) return false;
        if (Double.compare(that.longitud, longitud) != 0) return false;
        if (hospital != null ? !hospital.equals(that.hospital) : that.hospital != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idHospital;
        result = 31 * result + (hospital != null ? hospital.hashCode() : 0);
        temp = Double.doubleToLongBits(latitud);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitud);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
