package com.example.demo.dto;

public class RecomendacionDto {

    private int idRecomendacion;
    private String recomendacion;
    private int idPaciente;

    public RecomendacionDto(String recomendacion, int idPaciente) {
        this.recomendacion = recomendacion;
        this.idPaciente = idPaciente;
    }

    public RecomendacionDto(int idRecomendacion, String recomendacion, int idPaciente) {
        this.idRecomendacion = idRecomendacion;
        this.recomendacion = recomendacion;
        this.idPaciente = idPaciente;
    }

    public int getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
