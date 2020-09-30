package com.example.demo.controller;


import com.example.demo.bl.RegisterBl;
import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.security.AccessControlException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class pacienteController {
    RegisterBl registerBl;
    DoctorRepository doctorRepository;
    PacienteRepository pacienteRepository;

    @Autowired
    public pacienteController(RegisterBl registerBl,PacienteRepository pacienteRepository, DoctorRepository doctorRepository) {
        this.registerBl = registerBl;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(value = "/logindoctor", method = RequestMethod.POST)
    public ResponseEntity loginDoc(@RequestBody DoctorEntity doctorRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        registerBl.checkLogin(doctorRequest.getUsername(),doctorRequest.getPass(),"doctor");
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginPac(@RequestBody PacienteEntity pacienteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        registerBl.checkLogin(pacienteRequest.getUsername(),pacienteRequest.getPass(),"paciente");
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.ACCEPTED);
    }

    @RequestMapping("/subToDoc")
    public String subToDoc(){
        //TODO: Recuperar campos del form de suscripcion a doctor
        registerBl.subToDoc(1,1);
        return "Suscrito a doctor";
    }

    @RequestMapping(value = "/regisdoct", method = RequestMethod.POST)
    public ResponseEntity registerDoctor(@RequestBody DoctorEntity doctorRequest, BindingResult bindingResult){
        //TODO: Recuper formulario de registro
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        registerBl.registrarDoctor(new DoctorDto(doctorRequest.getFirstName(),doctorRequest.getLastName(),doctorRequest.getCi(),
                doctorRequest.getCorreo(),doctorRequest.getUsername(),doctorRequest.getPass()));
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerPaciente(@RequestBody PacienteEntity pacienteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        registerBl.registrarPaciente(new PacienteDto(pacienteRequest.getFirstName(),pacienteRequest.getLastName(),pacienteRequest.getCi(),
                pacienteRequest.getCorreo(),"Sano",0,pacienteRequest.getUsername(),pacienteRequest.getPass()));
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.CREATED);
    }

    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }

    @RequestMapping("/menoswaso")
    public String menoswaso(){
        return registerBl.getFullName();
    }
}

