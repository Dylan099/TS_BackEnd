package com.example.demo.controller;


import com.example.demo.bl.RegisterBl;
import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.dto.SuscripcionDto;
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
        if(registerBl.checkLogin(doctorRequest.getCorreo(),doctorRequest.getPass(),"doctor"))
            return new ResponseEntity(doctorRepository.findDoctorEntityByCorreo(doctorRequest.getCorreo()).getIdDoctor(), HttpStatus.ACCEPTED);
        else
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginPac(@RequestBody PacienteEntity pacienteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.checkLogin(pacienteRequest.getCorreo(),pacienteRequest.getPass(),"paciente"))
            return new ResponseEntity(new Mensaje("Aceptado"), HttpStatus.ACCEPTED);
        else
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public ResponseEntity subToDoc(@RequestBody SuscripcionDto suscripcionDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        int idPaciente = pacienteRepository.findPacienteEntityByCorreo(suscripcionDto.getCorreoPaciente()).getIdPaciente();
        int idDoctor = doctorRepository.findDoctorEntityByCorreo(suscripcionDto.getCorreoDoctor()).getIdDoctor();
        registerBl.subToDoc(idPaciente,idDoctor);
        return new ResponseEntity(new Mensaje("Se suscribio correctamente"),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/regisdoct", method = RequestMethod.POST)
    public ResponseEntity registerDoctor(@RequestBody DoctorEntity doctorRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.verificarCorreoExistenteDoctor(doctorRequest.getCorreo()))
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        registerBl.registrarDoctor(new DoctorDto(doctorRequest.getFirstName(),doctorRequest.getLastName(),doctorRequest.getCi(),
                doctorRequest.getCorreo(),doctorRequest.getUsername(),doctorRequest.getPass()));
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerPaciente(@RequestBody PacienteEntity pacienteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.verificarCorreoExistentePaciente(pacienteRequest.getCorreo()))
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        registerBl.registrarPaciente(new PacienteDto(pacienteRequest.getFirstName(),pacienteRequest.getLastName(),pacienteRequest.getCi(),
                pacienteRequest.getCorreo(),pacienteRequest.getSexo(),pacienteRequest.getEdad(),1,0,pacienteRequest.getUsername(),pacienteRequest.getPass()));
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.CREATED);
    }

    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }
}

