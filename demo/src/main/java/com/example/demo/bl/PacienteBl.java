package com.example.demo.bl;

import com.example.demo.bot.BotInit;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.Estatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class PacienteBl {
    PacienteRepository pacienteRepository;
    DoctorBl doctorBl;
    BotInit botBl;

    @Autowired
    public PacienteBl(PacienteRepository pacienteRepository, DoctorBl doctorBl, BotInit botBl) {
        this.pacienteRepository = pacienteRepository;
        this.doctorBl = doctorBl;
        this.botBl = botBl;
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

    public void delete_paciente(PacienteEntity pacienteEntity) {
        pacienteEntity.setEstatus(Estatus.INACTIVE.getStatus());
        pacienteRepository.save(pacienteEntity);
    }

    public boolean checkChatIdPac(int idPaciente) throws TelegramApiException {
        PacienteEntity pacienteEntity = pacienteRepository.findPacienteEntityByIdPaciente(idPaciente);
        System.out.println("ChatID: "+ pacienteEntity.getTelNum());
        System.out.println(pacienteEntity.getTelNum() != null && !pacienteEntity.getTelNum().isEmpty());
        if(pacienteEntity.getTelNum() != null && !pacienteEntity.getTelNum().isEmpty()){
            botBl.sendCodePassPac(pacienteEntity);
            return true;
        }
        return false;
    }

    public String createCodeNewPassPac(int idPaciente){
        PacienteEntity pacienteEntity = pacienteRepository.findPacienteEntityByIdPaciente(idPaciente);
        String code = generateCodePassPac(idPaciente);
        pacienteEntity.setLastCode(code);
        pacienteRepository.save(pacienteEntity);
        return code;
    }

    public boolean setNewPassPac(int idPaciente, String pass){
        PacienteEntity pacienteEntity = pacienteRepository.findPacienteEntityByIdPaciente(idPaciente);
        if(pacienteEntity!=null && !pass.isEmpty()){
            pacienteEntity.setPass(pass);
            pacienteRepository.save(pacienteEntity);
            return true;
        }
        return false;
    }

    private String generateCodePassPac(int id){
        String code = String.valueOf(id)+";"+(String.valueOf(Math.random() * (9999 - 999)) + 999);
        System.out.println("Generated Code: ->"+code);
        return code;
    }

    public boolean checkCodePac(int idPaciente,String code) throws TelegramApiException {
        PacienteEntity pacienteEntity = pacienteRepository.findPacienteEntityByIdPaciente(idPaciente);
        System.out.println(pacienteEntity.getLastCode()+" "+code);

        if(pacienteEntity.getLastCode().equals(code)){
            return true;
        }else{
            System.out.println("Codigo nulo");
            return false;
        }
    }


}
