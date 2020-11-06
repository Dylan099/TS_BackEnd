package com.example.demo.bot;

import com.example.demo.bl.DoctorBl;
import com.example.demo.bl.PacienteBl;
import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Random;

public class TelegramBot extends TelegramLongPollingBot {

    public DoctorRepository doctorBl;
    public PacienteRepository pacienteBl;

    @Autowired
    public TelegramBot(DoctorRepository doctorBl,PacienteRepository pacienteBl) {
        this.doctorBl = doctorBl;
        this.pacienteBl = pacienteBl;
    }

    @Override
    public String getBotToken() {
        return "1491355861:AAElKEVJxCL12xxSLVdCelV37AgmJ_w4A2k";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            SendMessage message;
            if(update.getMessage().getText().equals("/start")){
                message= new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Bienvenido, Ingrese el codigo provisto en la pagina web para activar el bot");
            }else{
                DoctorEntity doctorEntity = doctorBl.findDoctorEntityByLastCod(update.getMessage().getText());
                if(update.getMessage().getText().contains("-")){
                    if(doctorEntity!=null){
                        doctorEntity.setTelNum(update.getMessage().getChatId().toString());
                        doctorBl.save(doctorEntity);
                        message= new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("Gracias, para recuperar su contraseña solicite un nuevo codigo en la pagina");
                    }else{
                        message= new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("No pudimos detectar el codigo que ingreso, por favor revise la pagina e intente nuevamente");
                    }
                } else if(update.getMessage().getText().contains(";")) {
                    PacienteEntity pacienteEntity = pacienteBl.findPacienteEntityByLastCode(update.getMessage().getText());
                    if (pacienteEntity != null) {
                        pacienteEntity.setTelNum(update.getMessage().getChatId().toString());
                        pacienteBl.save(pacienteEntity);
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("Gracias, para recuperar su contraseña solicite un nuevo codigo en la pagina");
                    } else {
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("No pudimos detectar el codigo que ingreso, por favor revise la pagina e intente nuevamente");
                    }
                }else {
                    if(doctorEntity!=null)
                    {
                        doctorEntity.setDobleAuth(1);
                        doctorEntity.setTelNum(update.getMessage().getChatId().toString());
                        doctorBl.save(doctorEntity);
                        message= new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("Gracias, ahora podra recibir actualizaciones de su cuenta por este medio");
                    }else{
                        message= new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("No pudimos detectar el codigo que ingreso, por favor revise la pagina e intente nuevamente");
                    }
                }
            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "tallerSisBot";
    }

    public void sendToNumber(String telnum, String code) throws TelegramApiException {
        SendMessage message = new SendMessage()
                .setChatId(telnum)
                .setText("Este es su codigo para completar su solicitud: \n"+code);
        execute(message);
    }

}
