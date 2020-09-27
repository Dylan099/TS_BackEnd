package com.example.demo.dao;

import com.example.demo.domain.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity,Integer> {
    PacienteEntity findPacienteEntityByUsername(String user);
    PacienteEntity findPacienteEntityByIdPaciente(int id);
}
