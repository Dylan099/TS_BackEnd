package com.example.demo.bot;

import com.example.demo.bl.DoctorBl;
import com.example.demo.bl.PacienteBl;
import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInit {


    TelegramBot telegramBot;
    DoctorRepository doctorBl;
    PacienteRepository pacienteBl;

    @Autowired
    public BotInit(DoctorRepository doctorBl, PacienteRepository pacienteBl) {
        this.doctorBl = doctorBl;
        this.pacienteBl = pacienteBl;
    }

    public BotInit() {
    }

    @PostConstruct
    public void init() {

        // TODO Initialize Api Context
        ApiContextInitializer.init();

        // TODO Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // TODO Register our bot
        try {
            telegramBot = new TelegramBot(doctorBl,pacienteBl);
            botsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //Para doctor
    public void sendCode(DoctorEntity doctorEntity) throws TelegramApiException {
        String code = generateCode(doctorEntity.getIdDoctor());
        telegramBot.sendToNumber(doctorEntity.getTelNum(),code);
        doctorEntity.setLastCod(code);
        doctorBl.save(doctorEntity);
    }

    private String generateCode(int id){
        String code = String.valueOf(id)+""+(String.valueOf(Math.random() * (9999 - 999)) + 999);
        System.out.println("Generated Code: ->"+code);
        return code;
    }

    public void sendCodePass(DoctorEntity doctorEntity) throws TelegramApiException {
        String code = generateCodePass(doctorEntity.getIdDoctor());
        telegramBot.sendToNumber(doctorEntity.getTelNum(),code);
        doctorEntity.setLastCod(code);
        doctorBl.save(doctorEntity);
    }


    private String generateCodePass(int id){
        String code = String.valueOf(id)+"-"+(String.valueOf(Math.random() * (9999 - 999)) + 999);
        System.out.println("Generated Code: ->"+code);
        return code;
    }

    //PARA PACIENTE


    public void sendCodePassPac(PacienteEntity pacienteEntity) throws TelegramApiException {
        String code = generateCodePassPac(pacienteEntity.getIdPaciente());
        telegramBot.sendToNumber(pacienteEntity.getTelNum(),code);
        pacienteEntity.setLastCode(code);
        pacienteBl.save(pacienteEntity);
    }

    private String generateCodePassPac(int id){
        String code = String.valueOf(id)+"&"+(String.valueOf(Math.random() * (9999 - 999)) + 999);
        System.out.println("Generated Code: ->"+code);
        return code;
    }


}
