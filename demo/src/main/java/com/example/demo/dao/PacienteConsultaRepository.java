package com.example.demo.dao;

import com.example.demo.domain.PacienteConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteConsultaRepository extends JpaRepository<PacienteConsultaEntity,Integer>{
    List<PacienteConsultaEntity> findAllByIdPaciente(int paciente);
}
