package com.example.demo.bl;

import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteBl {
    PacienteRepository pacienteRepository;
    DoctorBl doctorBl;

    @Autowired
    public PacienteBl(PacienteRepository pacienteRepository, DoctorBl doctorBl) {
        this.pacienteRepository = pacienteRepository;
        this.doctorBl = doctorBl;
    }

    public PacienteEntity findById(int id){
        return pacienteRepository.findPacienteEntityByIdPaciente(id);
    }

    public List<PacienteEntity> findSex(int idDoc, String sex){
        if(!sex.isEmpty()){
            return pacienteRepository.findAllBySexoEqualsAndIdDoctorEquals(sex,idDoc);
        }else
            System.out.println("ERROR SEARCH BY SEX");
        return null;
    }

    public List<PacienteEntity> findStatus(int idDoc,int status){
        return pacienteRepository.findAllByIdStatusAndIdDoctorEquals(status,idDoc);
    }

    public List<PacienteEntity> findAge(int idDoc, String age){
        return pacienteRepository.findAllByEdadEqualsAndIdDoctorEquals(age,idDoc);
    }

    public List<PacienteEntity> findName(int idDoc, String age){
        return pacienteRepository.findAllByFirstNameEqualsAndIdDoctorEquals(age,idDoc);
    }

    public List<PacienteEntity> findApellido(int idDoc, String age){
        return pacienteRepository.findAllByLastNameEqualsAndIdDoctorEquals(age,idDoc);
    }

    public PacienteEntity recuperar_datos(int idPaciente) {
        return  pacienteRepository.findPacienteEntityByIdPaciente(idPaciente);
    }

    public void actualizar_datos(PacienteEntity pacienteEntity) {
        pacienteRepository.save(pacienteEntity);
    }
}
