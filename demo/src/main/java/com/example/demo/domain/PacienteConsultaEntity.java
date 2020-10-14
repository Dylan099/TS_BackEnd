package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "paciente_consulta", schema = "tds", catalog = "")
public class PacienteConsultaEntity {
    private int idPacienteConsulta;
    private int idPaciente;
    private int idConsulta;

    @Id
    @Column(name = "id_paciente_consulta")
    public int getIdPacienteConsulta() {
        return idPacienteConsulta;
    }

    public void setIdPacienteConsulta(int idPacienteConsulta) {
        this.idPacienteConsulta = idPacienteConsulta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacienteConsultaEntity that = (PacienteConsultaEntity) o;

        if (idPacienteConsulta != that.idPacienteConsulta) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idPacienteConsulta;
    }

    @Basic
    @Column(name = "id_paciente")
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Basic
    @Column(name = "id_consulta")
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }
}
