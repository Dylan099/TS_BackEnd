package com.example.demo.dao;

import com.example.demo.domain.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {
    List<DoctorEntity> findAll();
    DoctorEntity findDoctorEntityByIdDoctor(int id);
    DoctorEntity findDoctorEntityByUsername(String user);
    DoctorEntity findDoctorEntityByCorreo(String correo);
    DoctorEntity findDoctorEntityByCorreoAndEstatus(String correo, int estatus);
    DoctorEntity findDoctorEntityByLastCod(String code);
    @Query(
            value = "SELECT * FROM doctor p \n" +
                    "WHERE p.first_name = ?1 \n" +
                    "    AND p.last_name = ?2 \n" +
                    "    AND p.ci = ?3 \n" +
                    "    AND p.correo = ?4 \n" +
                    "    AND p.username = ?5 \n" +
                    "    AND p.pass = ?6 \n" +
                    "    AND p.estatus = ?7",
            nativeQuery = true)
    List<DoctorEntity> existsByDoctorEntity( String firstname, String lastname, String ci, String correo, String username, String pass, int status);
}
