package com.example.demo.bl;

import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.PacienteEntity;
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

    public List<PacienteEntity> findSex(String sex){
        if(!sex.isEmpty()){
            return pacienteRepository.findAllBySexoEquals(sex);
        }else
            System.out.println("ERROR SEARCH BY SEX");
        return null;
    }

    public List<PacienteEntity> findStatus(int status){
        return pacienteRepository.findAllByIdStatus(status);
    }

    public List<PacienteEntity> findAge(String age){
        return pacienteRepository.findAllByEdadEquals(age);
    }
}
