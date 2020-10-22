package com.example.demo.dao;

import com.example.demo.domain.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {
    List<DoctorEntity> findAll();
    DoctorEntity findDoctorEntityByIdDoctor(int id);
    DoctorEntity findDoctorEntityByUsername(String user);
    DoctorEntity findDoctorEntityByCorreo(String correo);
    DoctorEntity findDoctorEntityByCorreoAndEstatus(String correo, int estatus);
}
