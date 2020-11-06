package com.example.demo.dao;

import com.example.demo.domain.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity,Integer> {
    PacienteEntity findPacienteEntityByUsername(String user);
    PacienteEntity findByIdPaciente(int id);

    @Query(
            value = "SELECT p.num_recomendacion FROM paciente p WHERE p.id_paciente = ? ",
            nativeQuery = true)
    int findCountNumRecomendacion(int id_paciente);


    PacienteEntity findPacienteEntityByIdPaciente(int id);

    List<PacienteEntity> findAllByIdDoctorAndEstatus(int id, int estatus);

    PacienteEntity findPacienteEntityByCorreo(String correo);

    PacienteEntity findPacienteEntityByCorreoAndEstatus(String correo, int estatus);

    @Query(
            value = "SELECT p.first_name FROM paciente p WHERE p.id_doctor = ?1",
            nativeQuery = true)
    List<String> findFirstNameByIdDoctor(int id);

    List<PacienteEntity> findAllBySexoEqualsAndIdDoctorEquals(String sexo, int id);
    List<PacienteEntity> findAllByIdStatusAndIdDoctorEquals(int status, int id);
    List<PacienteEntity> findAllByEdadEqualsAndIdDoctorEquals(String edad, int id);
    List<PacienteEntity> findAllByFirstNameEqualsAndIdDoctorEquals(String name, int id);
    List<PacienteEntity> findAllByLastNameEqualsAndIdDoctorEquals(String surname, int id);

    @Query(
            value = "SELECT COUNT(p.id_paciente) FROM paciente p WHERE p.id_doctor = ? AND p.id_status = ?",
            nativeQuery = true)
    int findCountPacienteStatus(int id_doctor,int id_status);

    @Query(
            value = "SELECT COUNT(p.id_paciente) FROM paciente p WHERE p.id_doctor = ? AND p.sexo = ? AND p.id_status=?",
            nativeQuery = true)
    int findCountPacienteSexo(int id_doctor,String sexo,int id_status);

    @Query(
            value = "SELECT COUNT(p.id_paciente)  FROM paciente p WHERE p.id_doctor = ? AND p.id_status=? GROUP BY(p.edad)",
            nativeQuery = true)
    int[] findCountPacienteEdad(int id_doctor,int id_status);

    @Query(
            value = "SELECT p.edad FROM paciente p WHERE p.id_doctor = ? AND p.id_status=? GROUP BY(p.edad)",
            nativeQuery = true)
    int[] findCountPacienteEdades(int id_doctor,int id_status);

    PacienteEntity findPacienteEntityByLastCode(String lastCode);


}
