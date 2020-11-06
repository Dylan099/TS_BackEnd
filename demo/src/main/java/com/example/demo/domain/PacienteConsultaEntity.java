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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacienteConsultaEntity that = (PacienteConsultaEntity) o;

        if (idPacienteConsulta != that.idPacienteConsulta) return false;
        if (idPaciente != that.idPaciente) return false;
        if (idConsulta != that.idConsulta) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPacienteConsulta;
        result = 31 * result + idPaciente;
        result = 31 * result + idConsulta;
        return result;
    }
}
