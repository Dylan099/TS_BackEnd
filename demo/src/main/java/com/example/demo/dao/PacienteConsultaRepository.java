package com.example.demo.dao;

import com.example.demo.domain.PacienteConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteConsultaRepository extends JpaRepository<PacienteConsultaEntity,Integer>{
    List<PacienteConsultaEntity> findAllByIdPaciente(int paciente);

    @Query(value = "SELECT p.id_consulta FROM paciente_consulta p WHERE p.id_paciente = ?1  ORDER BY p.id_consulta DESC LIMIT 1 ",
            nativeQuery = true)
    int findIdConsutaByIdPaciente(int idPaciente);
}
