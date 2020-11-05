package com.example.demo.bl;

import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.Estatus;
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
        System.out.println("USERNAME: "+ user);
        System.out.println("Pass: "+pass);
        System.out.println("Tipo: "+tipo);
        try{
            if(tipo.equals("doctor")){
                DoctorEntity dc = doctorRepository.findDoctorEntityByCorreoAndEstatus(user, Estatus.ACTIVE.getStatus());
                System.out.println("Encontrado" + pass + "Estatus: "+dc.getPass().equals(pass));
                return dc.getPass().equals(pass);
            }else if(tipo.equals("paciente")){
                PacienteEntity pc = pacienteRepository.findPacienteEntityByCorreoAndEstatus(user, Estatus.ACTIVE.getStatus());
                return pc.getPass().equals(pass);
            }
            return false;
        }catch (NullPointerException e){
            System.out.println("Exploto");
            return true;
        }
    }

    public void registrarPaciente(PacienteDto pacienteDto){
        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntity.setFirstName(pacienteDto.getFirstName());
        pacienteEntity.setLastName(pacienteDto.getLastName());
        pacienteEntity.setCi(pacienteDto.getCi());
        pacienteEntity.setIdStatus(1);
        pacienteEntity.setSexo(pacienteDto.getSexo());
        pacienteEntity.setEdad(pacienteDto.getEdad());
        pacienteEntity.setCorreo(pacienteDto.getCorreo());
        pacienteEntity.setUsername(pacienteDto.getUsername());
        pacienteEntity.setPass(pacienteDto.getPass());
        pacienteEntity.setIdDoctor(null);
        pacienteEntity.setEstatus(Estatus.ACTIVE.getStatus());
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
        doctorEntity.setEstatus(Estatus.ACTIVE.getStatus());
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

    public boolean verificarCorreoExistentePaciente(String correo){
        PacienteEntity pacienteEntity= pacienteRepository.findPacienteEntityByCorreoAndEstatus(correo, Estatus.ACTIVE.getStatus());
        List<PacienteEntity> pacienteEntityList = pacienteRepository.findAll();
        return pacienteEntityList.contains(pacienteEntity);
    }

    public boolean verificarCorreoExistenteDoctor(String correo){
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByCorreoAndEstatus(correo, Estatus.ACTIVE.getStatus());
        List<DoctorEntity> doctorEntityList = doctorRepository.findAll();
        return doctorEntityList.contains(doctorEntity);
    }
}
