package com.example.demo.dao;

import com.example.demo.domain.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity,Integer> {
    PacienteEntity findPacienteEntityByUsername(String user);

    PacienteEntity findPacienteEntityByIdPaciente(int id);

    List<PacienteEntity> findAllByIdDoctor(int id);

    PacienteEntity findPacienteEntityByCorreo(String correo);

    @Query(
            value = "SELECT p.first_name FROM paciente p WHERE p.id_doctor = ?1",
            nativeQuery = true)
    List<String> findFirstNameByIdDoctor(int id);

    List<PacienteEntity> findAllBySexoEqualsAndIdDoctorEquals(String sexo, int id);
    List<PacienteEntity> findAllByIdStatusAndIdDoctorEquals(int status, int id);
    List<PacienteEntity> findAllByEdadEqualsAndIdDoctorEquals(String edad, int id);
    List<PacienteEntity> findAllByFirstNameEqualsAndIdDoctorEquals(String name, int id);
    List<PacienteEntity> findAllByLastNameEqualsAndIdDoctorEquals(String surname, int id);
    
}