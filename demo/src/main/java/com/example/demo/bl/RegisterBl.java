package com.example.demo.bl;

import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterBl {
    DoctorRepository doctorRepository;
    PacienteRepository pacienteRepository;

    @Autowired
    public RegisterBl(DoctorRepository doctorRepository, PacienteRepository pacienteRepository) {
        this.doctorRepository = doctorRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public boolean checkLogin(String user, String pass, String tipo){

        try{
            if(tipo.equals("doctor")){
                DoctorEntity dc = doctorRepository.findDoctorEntityByUsername(user);
                return dc.getPass().equals(pass);
            }else if(tipo.equals("paciente")){
                PacienteEntity pc = pacienteRepository.findPacienteEntityByUsername(user);
                return pc.getPass().equals(pass);
            }
            return false;
        }catch (NullPointerException e){
            return false;
        }
    }

    public void registrarPaciente(PacienteDto pacienteDto){
        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntity.setFirstName(pacienteDto.getFirstName());
        pacienteEntity.setLastName(pacienteDto.getLastName());
        pacienteEntity.setCi(pacienteDto.getCi());
        pacienteEntity.setLastStatus(pacienteDto.getLastStatus());
        pacienteEntity.setCorreo(pacienteDto.getCorreo());
        pacienteEntity.setUsername(pacienteDto.getUser());
        pacienteEntity.setPass(pacienteDto.getPass());
        pacienteEntity.setIdDoctor(null);
        pacienteRepository.save(pacienteEntity);
    }

    public void registrarDoctor(DoctorDto doctorDto){
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName(doctorDto.getFirstName());
        doctorEntity.setLastName(doctorDto.getLastName());
        doctorEntity.setCi(doctorDto.getCi());
        doctorEntity.setCorreo(doctorDto.getCorreo());
        doctorEntity.setUsername(doctorDto.getUser());
        doctorEntity.setPass(doctorDto.getPass());
        doctorRepository.save(doctorEntity);
    }

    public void subToDoc(int id_paciente,int id_doctor){
        PacienteEntity pacienteEntity = pacienteRepository.findPacienteEntityByIdPaciente(id_paciente);
        pacienteEntity.setIdDoctor(id_doctor);
        pacienteRepository.save(pacienteEntity);

    }

    public String getFullName(){
        List<DoctorEntity> waso = doctorRepository.findAll();
        for (DoctorEntity de:waso
             ) {
            return de.getFirstName();
        }
        return doctorRepository.findDoctorEntityByIdDoctor(1).getFirstName()+doctorRepository.findDoctorEntityByIdDoctor(1).getLastName();
    }
}
